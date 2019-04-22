import postal from 'postal';
import Service, { ServerSentTaskEvent } from "./interfaces";
import axios from 'axios';

class ServiceImpl implements Service {
    todoListChannel: IChannelDefinition<ServerSentTaskEvent>;
    inProgressListChannel: IChannelDefinition<ServerSentTaskEvent>;
    doneListChannel: IChannelDefinition<ServerSentTaskEvent>;

    taskboardEventSource: EventSource;
    taskboardUrl: string = "http://localhost:8080/taskboard/";
    constructor() {
        this.taskboardEventSource = new EventSource(this.taskboardUrl + 'taskboardEvents');

        this.todoListChannel = postal.channel("todoList");
        this.inProgressListChannel = postal.channel("inProgressList");
        this.doneListChannel = postal.channel("doneList");

        this.initTodoListChannel();
        this.initDoneListChannel();
        this.initInProgressListChannel();
        this.registerEvents();
    }

    getTodoListChannel() {
        return this.todoListChannel;
    }
    getInProgressListChannel() {
        return this.inProgressListChannel;
    }
    getDoneListChannel() {
        return this.doneListChannel;
    }
    convertEvent(event: MessageEvent) {

        var serverSentTaskEvent: ServerSentTaskEvent = {
            id: event.lastEventId,
            data: JSON.parse(event.data)
        };

        return serverSentTaskEvent;

    }
    registerEvents() {


        this.taskboardEventSource.addEventListener('CreateTaskEvent', ((event: MessageEvent) => {
            console.info('CreateTaskEvent:' + event.lastEventId);
            this.todoListChannel.publish("add", this.convertEvent(event));
        }) as EventListener);

        this.taskboardEventSource.addEventListener('ClaimTaskEvent', ((event: MessageEvent) => {
            console.info('ClaimTaskEvent:' + event.lastEventId);
            this.todoListChannel.publish("remove", this.convertEvent(event));
            this.inProgressListChannel.publish("add", this.convertEvent(event));
        }) as EventListener);

        this.taskboardEventSource.addEventListener('SetAssigneeForTaskEvent', ((event: MessageEvent) => {
            console.info('SetAssigneeForTaskEvent:' + event.lastEventId);
            this.todoListChannel.publish("remove", this.convertEvent(event));
            this.inProgressListChannel.publish("add", this.convertEvent(event));
        }) as EventListener);

        this.taskboardEventSource.addEventListener('UnclaimTaskEvent', ((event: MessageEvent) => {
            console.info('UnclaimTaskEvent:' + event.lastEventId);
            this.inProgressListChannel.publish("remove", this.convertEvent(event));
            this.todoListChannel.publish("add", this.convertEvent(event));

        }) as EventListener);


        this.taskboardEventSource.addEventListener('CompleteTaskEvent', ((event: MessageEvent) => {
            console.info('CompleteTaskEvent:' + event.lastEventId);
            this.inProgressListChannel.publish("remove", this.convertEvent(event));
            this.doneListChannel.publish("add", this.convertEvent(event));
        }) as EventListener);
    }

    initTodoListChannel() {

        let channel = this.todoListChannel;

        axios.get(this.taskboardUrl + 'todoList').then(res => {

            res.data.forEach(function (value: any) {
                channel.publish("add", value);
            });
        })


    }

    initInProgressListChannel() {
        let channel = this.inProgressListChannel;

        axios.get(this.taskboardUrl + 'inProgressList').then(res => {

            res.data.forEach(function (value: any) {
                channel.publish("add", value);
            });
        })


    }

    initDoneListChannel() {
        let channel = this.doneListChannel;

        axios.get(this.taskboardUrl + 'doneList').then(res => {

            res.data.forEach(function (value: any) {
                channel.publish("add", value);
            });
        })

    }
}

export default (ServiceImpl);