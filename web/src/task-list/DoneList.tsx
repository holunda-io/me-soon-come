import * as React from "react";

import { List } from "@material-ui/core";

import { createStyles, withStyles } from "@material-ui/core/styles";
import { Theme } from "@material-ui/core/styles/createMuiTheme";

import { EmptyStateTaskList } from "../shared/emptyState";
import { ServerSentTaskEvent, ITaskListProps, ITaskListState } from "../shared/interfaces";

import DoneListItem from "./DoneListItem";


const styles = (theme: Theme) =>
  createStyles({
    thead: {
      fontSize: "1.2rem"
    },
  });


class DoneList extends React.Component<ITaskListProps, ITaskListState> {
  constructor(props: ITaskListProps) {
    super(props);
    this.state = {
      source: new EventSource('http://localhost:8080/taskboard/taskboardEvents'),
      tasksAvailable: false,
      expanded: true,
      taskEvents: []
    };

    this.handleClick = this.handleClick.bind(this);
  }

  handleClick = (taskEvent: ServerSentTaskEvent) => (event: any) => {

    console.info(taskEvent);
    const { taskEvents } = this.state;
    if (taskEvents) {
      var filtered = taskEvents.filter(function (t, index, arr) {

        return t.id != taskEvent.id;

      });
      this.setState({
        taskEvents: filtered
      });
    }
  }

  componentDidMount() {

    this.props.service.getDoneListChannel().subscribe("add", ((event) => {
      const { taskEvents } = this.state;
      taskEvents.push(event);
      this.setState({
        taskEvents: taskEvents,
        tasksAvailable: true
      });

    }));

  }

  public render() {
    const { classes } = this.props;

    const taskList = (
      <List dense className={classes.root}>

        {this.state.taskEvents ? this.state.taskEvents.map(taskEvent => {

          return <DoneListItem taskEvent={taskEvent}
            key={"task_list_item" + taskEvent.id}
            handleClick={this.handleClick} />
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

export default withStyles(styles)(DoneList);
