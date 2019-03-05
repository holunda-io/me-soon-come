import * as React from "react";
import { createStyles, DialogActions, Theme, withStyles, Card, CardContent, Typography, CardActions, CardHeader, Avatar, IconButton, Divider, Grid, Paper } from "@material-ui/core";
import { TaskItem } from "../shared/interfaces";
import Button from "@material-ui/core/Button/Button";
import Dialog from "@material-ui/core/Dialog/Dialog";
import DialogTitle from "@material-ui/core/DialogTitle/DialogTitle";
import DialogContent from "@material-ui/core/DialogContent/DialogContent";
import DialogContentText from "@material-ui/core/DialogContentText/DialogContentText";
import FormControl from "@material-ui/core/FormControl/FormControl";
import TextField from "@material-ui/core/es/TextField/TextField";
import axios from "axios";

export interface ITaskDialogProps {
  taskItem: TaskItem;
  classes: any;
}

export interface ITaskDialogState {
  open: boolean;
  maxWidth?: 'xs' | 'sm' | 'md' | 'lg' | false;
  fullWidth: boolean;
  comment: string;
}

const styles = (theme: Theme) =>
  createStyles({
    form: {
      display: 'flex',
      flexDirection: 'column',
      margin: 'auto',
      width: 'fit-content',
    },
    formControl: {
      marginTop: theme.spacing.unit * 2,
      minWidth: 120,
    },
    formControlLabel: {
      marginTop: theme.spacing.unit,
    },
  });

const date_format: string = "MM.DD.YYYY";

class TaskDialog extends React.Component<ITaskDialogProps, ITaskDialogState> {
  constructor(props: ITaskDialogProps) {
    super(props);
    this.state = {
      open: false,
      fullWidth: true,
      maxWidth: 'sm',
      comment: ''
    };
  }

  handleRejection = () => {
    this.sendDecision('REJECT');
  }

  handleApproval = () => {
    this.sendDecision('APPROVE');
  }
  handleReturn = () => {
    this.sendDecision('RETURN');
  }
  sendDecision = (decision: string) => {
    console.log(this.props.taskItem);
    var url = 'http://localhost:8080/request/' + this.props.taskItem.task.businessKey + '/decision/' + decision;

    axios.post(url, {
      params: {
        comment: this.state.comment
      }
    }).then(res => {
      this.setState({
        open: false
      });
    })

  }

  handleClickOpen = () => {
    console.log(this.props.taskItem);
    this.setState({ open: true });
  };

  handleClose = () => {
    this.setState({ open: false });
  };

  handleChange = (name: string) => (event: any) => {

    this.setState({
      comment: event.target.value,
    });
  };

  render() {
    const { classes } = this.props;

    return (
      <React.Fragment>
        <Button variant="outlined" color="primary" onClick={this.handleClickOpen}>
          Approve
        </Button>
        <Dialog
          fullWidth={this.state.fullWidth}
          maxWidth={this.state.maxWidth}
          open={this.state.open}
          onClose={this.handleClose}
          aria-labelledby="max-width-dialog-title"
        >
          <DialogTitle id="max-width-dialog-title">{this.props.taskItem.task.name}</DialogTitle>
          <DialogContent>
        
            <Card className={classes.card}>
              <CardHeader
                avatar={
                  <Avatar aria-label="Return" className={classes.avatar}>
                    RT
            </Avatar>
                }
                title="return the request"
              />
              <CardContent>
                <Typography>
                  Send the request back to the originator with a comment.
                </Typography>
                <TextField
                  id="standard-name"
                  label="Comment"
                  multiline
                  className={this.props.classes.textField}
                  onChange={this.handleChange('multiline')}
                  value={this.state.comment}
                  margin="normal"
                />
              </CardContent>
              <CardActions>
                <Button onClick={this.handleReturn} color="primary">
                  Return
              </Button>
              </CardActions>
            </Card>
            <Card className={classes.card}>
              <CardHeader
                avatar={
                  <Avatar aria-label="Approve" className={classes.avatar}>
                    RT
            </Avatar>
                }
                title="approve the request"
              />
              <CardContent>
                <Typography>
                  Send the request back to the originator with a comment.
                </Typography>
                <TextField
                  id="standard-name"
                  label="Comment"
                  multiline
                  className={this.props.classes.textField}
                  onChange={this.handleChange('multiline')}
                  value={this.state.comment}
                  margin="normal"
                />
              </CardContent>
              <CardActions>
                <Button onClick={this.handleApproval} color="primary">
                  Approve
              </Button>
              </CardActions>
            </Card>

            <DialogContentText>
              {this.props.taskItem.task.id}
            </DialogContentText>
            <form className={classes.form} noValidate>
              <FormControl className={classes.formControl}>

              </FormControl>
              <Button onClick={this.handleRejection} color="primary">
                Reject
              </Button>
            </form>
          </DialogContent>
          <DialogActions>

            <Button onClick={this.handleClose} color="primary">
              Close
            </Button>
          </DialogActions>
        </Dialog>
      </React.Fragment>
    );
  }
}


export default withStyles(styles)(TaskDialog);
