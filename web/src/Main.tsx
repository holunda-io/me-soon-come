import * as React from "react";

import { Grid, Paper } from "@material-ui/core";
import { createStyles, withStyles } from "@material-ui/core/styles";
import { Theme } from "@material-ui/core/styles/createMuiTheme";
import Service from "./shared/interfaces";
import ServiceImpl from "./shared/service";
import TodoList from "./task-list/TodoList";
import InProgressList from "./task-list/InProgressList";
import DoneList from "./task-list/DoneList";


export interface IMainProps {
  classes: any;
}

export interface IMainState {
  service: Service
}

const styles = (theme: Theme) =>
  createStyles({

  });

class Main extends React.Component<IMainProps, IMainState> {
  constructor(props: IMainProps) {
    super(props);
    this.state = {
      service : new ServiceImpl("Werner")
    };
  }

  public render() {
    const { classes } = this.props;

    return (
      <React.Fragment>
        <Grid container
          direction="row"
          justify="space-around"
          alignItems="flex-start" className={classes.root}>
          <Grid item xs={3}>
            todo
            <TodoList />
          </Grid>
          <Grid item xs={3}>
            in progress
            <InProgressList />
          </Grid>
          <Grid item xs={3}>
            done
            <DoneList />
          </Grid>
        </Grid>
      </React.Fragment>
    );
  }
}

export default withStyles(styles)(Main);
