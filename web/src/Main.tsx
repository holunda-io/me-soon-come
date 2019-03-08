import * as React from "react";

import { Grid, Paper } from "@material-ui/core";
import { createStyles, withStyles } from "@material-ui/core/styles";
import { Theme } from "@material-ui/core/styles/createMuiTheme";
import TaskList from "./task-list/TaskList";
import { TaskItem } from "./shared/interfaces";
import TaskItemEditor from "./taks-editor/TaskItemEditor";


export interface IMainProps {
  classes: any;
}

export interface IMainState {
  selectedTaskItem?: TaskItem;
}

const styles = (theme: Theme) =>
  createStyles({
    
  });

class Main extends React.Component<IMainProps, IMainState> {
  constructor(props: IMainProps) {
    super(props);
    this.state = {
      selectedTaskItem: undefined
    };
    this.handleClick = this.handleClick.bind(this);
  }
  handleClick = (taskItem: TaskItem) => (event: any) => {
    this.setState({ selectedTaskItem: taskItem })
  }


  public render() {
    const { classes } = this.props;
    //const ti=this.state.selectedTaskItem;
    return (
      <React.Fragment>
        <Grid container
          direction="row"
          justify="space-around"
          alignItems="flex-start" className={classes.root}>
          <Grid item xs={3}>
            <TaskList handleClick={this.handleClick} />
          </Grid>
          <Grid item xs={9}>
            {this.state && this.state.selectedTaskItem ? (
              <TaskItemEditor taskItem={this.state.selectedTaskItem} />
            ) : (
                <Paper >select a task, nplease</Paper>
              )}
          </Grid>
        </Grid>
      </React.Fragment>
    );
  }
}

export default withStyles(styles)(Main);
