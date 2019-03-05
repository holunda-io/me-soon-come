import * as React from "react";

import { Grid, Paper, Fab } from "@material-ui/core";
import AddIcon from '@material-ui/icons/Add';
import { createStyles, withStyles } from "@material-ui/core/styles";
import { Theme } from "@material-ui/core/styles/createMuiTheme";
import TaskList from "./task-list/TaskList";
import { TaskItem } from "./shared/interfaces";
import TaskItemEditor from "./taks-editor/TaskItemEditor";
import NewRequestDialog from "./dialogs/NewRequestDialog";


export interface IMainProps {
  classes: any;
}

export interface IMainState {
  selectedTaskItem?: TaskItem;
  open: boolean;
  message: string;
}

const styles = (theme: Theme) =>
  createStyles({
    fab: {
      position: 'absolute',
      top: theme.spacing.unit * 2,
      right: theme.spacing.unit * 2,
    },
  });

class Main extends React.Component<IMainProps, IMainState> {
  constructor(props: IMainProps) {
    super(props);
    this.state = {
      selectedTaskItem: undefined,
      open: false,
      message: ""
    };
    this.handleClick = this.handleClick.bind(this);
  }
  handleClick = (taskItem: TaskItem) => (event: any) => {
    this.setState({ selectedTaskItem: taskItem })
  }

  openNewRequestDialog = (event: any) => {
    this.setState({ open: true });
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
            <Fab size="small"
              color="primary"
              aria-label="Add"
              className={classes.fab}
              onClick={this.openNewRequestDialog}
            >
              <AddIcon />
            </Fab>
            {this.state && this.state.selectedTaskItem ? (

              <TaskItemEditor taskItem={this.state.selectedTaskItem} />
            ) : (

                <Paper >select a task, nplease</Paper>
              )}
          </Grid>
        </Grid>
        <NewRequestDialog open={this.state.open} />
      </React.Fragment>
    );
  }
}

export default withStyles(styles)(Main);
