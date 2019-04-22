import * as React from "react";

import { List } from "@material-ui/core";

import { createStyles, withStyles } from "@material-ui/core/styles";
import { Theme } from "@material-ui/core/styles/createMuiTheme";

import { EmptyStateTaskList } from "../shared/emptyState";
import { ITaskListProps, ITaskListState } from "../shared/interfaces";

import InProgressListItem from "./InProgressListItem";

const styles = (theme: Theme) =>
  createStyles({
    thead: {
      fontSize: "1.2rem"
    },
  });


class InProgressList extends React.Component<ITaskListProps, ITaskListState> {
  constructor(props: ITaskListProps) {
    super(props);
    this.state = {
      source: new EventSource('http://localhost:8080/taskboard/taskboardEvents'),
      tasksAvailable: false,
      expanded: true,
      taskEvents: []
    };
  }


  componentDidMount() {


    this.props.service.getInProgressListChannel().subscribe("add", ((event) => {
      const { taskEvents } = this.state;
      taskEvents.push(event);
      this.setState({
        taskEvents: taskEvents,
        tasksAvailable: true
      });
    }));
    this.props.service.getInProgressListChannel().subscribe("remove", ((event) => {
      const { taskEvents } = this.state;
      this.setState({
        tasksAvailable: true,
        taskEvents: taskEvents.filter(e => e.id !== event.id)
      });
    }));

  }

  public render() {
    const { classes } = this.props;

    const taskList = (
      <List dense className={classes.root}>

        {this.state.taskEvents ? this.state.taskEvents.map(taskEvent => {

          return <InProgressListItem taskEvent={taskEvent} key={"task_list_item" + taskEvent.id} />
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

export default withStyles(styles)(InProgressList);
