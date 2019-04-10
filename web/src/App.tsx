import React, { Component } from "react";


import "./App.scss";

//Material-UI Themeing import
import MuiThemeProvider from "@material-ui/core/styles/MuiThemeProvider";
import theme from "./theme";
import CssBaseline from "@material-ui/core/CssBaseline";

import Typography from "@material-ui/core/Typography";

import { EmptyStateLoginPage } from "./shared/emptyState";
import Main from "./Main";



export default class App extends Component {
  state = {
    auth: false
  };

  handleNavbarState = (childState: any) => {
    this.setState({
      auth: childState.auth
    });
  };

  render() {
    const { auth } = this.state;
    return (
      <MuiThemeProvider theme={theme}>
        <div>
          <CssBaseline />
          <Typography />
          {!auth ? (
              <Main />
          ) : (
            <EmptyStateLoginPage />
          )}
        </div>
      </MuiThemeProvider>
    );
  }
}
