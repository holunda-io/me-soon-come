import * as React from "react";

import { List } from "@material-ui/core";

import { createStyles, withStyles } from "@material-ui/core/styles";
import { Theme } from "@material-ui/core/styles/createMuiTheme";

import { EmptyStateTaskList } from "../shared/emptyState";
import { TaskItem, Result } from "../shared/interfaces";
import axios from 'axios';

import TaskListItem from "./TaskListItem";

export interface ITaskListProps {
  classes: any;
  handleClick: any;
}

export interface ITaskListState {
  tasksAvailable: boolean;
  result?: Result;
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
      tasksAvailable: false,
      expanded: true
    };
  }


  componentDidMount() {
    let result: Result;
    axios.get('http://localhost:8080/api/task').then(res => {

      this.setState({
        result: res.data,
        tasksAvailable: true
      });
    })
  }

  public render() {
    const { classes } = this.props;

    const taskList = (
      <List dense className={classes.root}>

        {this.state.result ? this.state.result.hits.hits.map(hit => {

          return <TaskListItem taskItem={hit._source}
            handleClick={this.props.handleClick} />
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
