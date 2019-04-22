
export interface Task {
  id: string,
  name: string,
  description: string,
  url: string,
  formKey: string,
  candidateGroups: string[],
  candidateUsers: string[],
  assignee: string,
  processName: string,
  createTime: Date,
  dueDate: Date,
  businessKey: string,
  priority: number,
  payload: Payload,
  followUpDate: Date
}

export interface Payload {
  request: string,
  originator: string,
  comment: any
}

export interface ApprovalRequest {
  amount: number,
  currency: string,
  id: string,
  subject: string,
  applicant: string

}

export interface User {
  userId: string
}
export interface ServerSentTaskEvent {
  id: string,
  data: Task
}

export default interface Service {
  getTodoListChannel():IChannelDefinition<ServerSentTaskEvent>;
  getInProgressListChannel():IChannelDefinition<ServerSentTaskEvent>;
  getDoneListChannel():IChannelDefinition<ServerSentTaskEvent>;
}

export interface ITaskListProps {
  classes: any;
  service: Service;
}

export interface ITaskListState {
  source: EventSource;
  tasksAvailable: boolean;
  taskEvents: ServerSentTaskEvent[];
  expanded: boolean
}
