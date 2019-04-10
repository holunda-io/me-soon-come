import * as React from "react";

import { List } from "@material-ui/core";

import { createStyles, withStyles } from "@material-ui/core/styles";
import { Theme } from "@material-ui/core/styles/createMuiTheme";

import { EmptyStateTaskList } from "../shared/emptyState";
import { Task, ServerSentTaskEvent } from "../shared/interfaces";
import axios from 'axios';

import TaskListItem from "./TaskListItem";

export interface ITaskListProps {
  classes: any;
}

export interface ITaskListState {
  source: EventSource;
  tasksAvailable: boolean;
  taskEvents?: ServerSentTaskEvent[];
  expanded: boolean
}

const styles = (theme: Theme) =>
  createStyles({
    thead: {
      fontSize: "1.2rem"
    },
  });


class TaskList extends React.Component<ITaskListProps, ITaskListState> {
  constructor(props: ITaskListProps) {
    super(props);
    this.state = {
      source: new EventSource('http://localhost:8080/taskboard/taskboardEvents'),
      tasksAvailable: false,
      expanded: true
    };
  }


  componentDidMount() {

    const { source } = this.state;
    axios.get('http://localhost:8080/taskboard/newTasks').then(res => {

      this.setState({
        taskEvents: res.data,
        tasksAvailable: true
      });
    })
    source.addEventListener('CreateTaskEvent', ((event: MessageEvent) => {
      const { taskEvents } = this.state;
      
      console.info(event);
      
      let newTaskEvent:ServerSentTaskEvent ={
        id:event.lastEventId,
        data:JSON.parse(event.data),
        event:event.origin
      }
      if (taskEvents) {
        let newTaskEvents: ServerSentTaskEvent[] = taskEvents;
        newTaskEvents.push(newTaskEvent);
        this.setState({ taskEvents: newTaskEvents });
      }
    }) as EventListener);

  }

  public render() {
    const { classes } = this.props;

    const taskList = (
      <List dense className={classes.root}>

        {this.state.taskEvents ? this.state.taskEvents.map(taskEvent => {

          return <TaskListItem taskEvent={taskEvent} key={"task_list_item" + taskEvent.id} />
        }) : <EmptyStateTaskList />}

      </List>
    );

    return (
      <React.Fragment>
        {this.state.tasksAvailable ? (
          taskList
        ) : (
            <EmptyStateTaskList />
          )}

      </React.Fragment>
    );
  }
}

export default withStyles(styles)(TaskList);
