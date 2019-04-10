import * as React from "react";

import { Grid, Paper } from "@material-ui/core";
import { createStyles, withStyles } from "@material-ui/core/styles";
import { Theme } from "@material-ui/core/styles/createMuiTheme";
import TaskList from "./task-list/TaskList";


export interface IMainProps {
  classes: any;
}

export interface IMainState {
}

const styles = (theme: Theme) =>
  createStyles({
    
  });

class Main extends React.Component<IMainProps, IMainState> {
  constructor(props: IMainProps) {
    super(props);
    this.state = { 
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
            <TaskList />
          </Grid>
        </Grid>
      </React.Fragment>
    );
  }
}

export default withStyles(styles)(Main);
