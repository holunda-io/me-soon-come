import React, { Component } from 'react';

import './App.css';
import axios from 'axios';
import { Configuraion } from './shared/interfaces';

import { createStyles, withStyles } from "@material-ui/core/styles";
import { Theme } from "@material-ui/core/styles/createMuiTheme";
import { Paper, Typography } from "@material-ui/core";
import Autocomplete from './Autocomplete';

export interface IAppProps {
  classes: any
}
export interface IAppState {
  configuration?: Configuraion
}

const styles = (theme: Theme) =>
  createStyles({

  });

class App extends Component<IAppProps, IAppState> {
  constructor(props: IAppProps) {
    super(props);
    this.state = {
      configuration: undefined
    };
  }
  componentDidMount() {
    axios.get('http://localhost:8080/admin/api/configuration').then(res => {
      console.log(res.data);
      this.setState({
        configuration: res.data
      });
    })
  }

  render() {
    return (
      <div>
        <Paper className={this.props.classes.root} elevation={1}>
          <Autocomplete/>
          <Typography component="p">
            {JSON.stringify(this.state.configuration)}
        </Typography>
        </Paper>
      </div>
    );
  }
}

export default withStyles(styles)(App);
