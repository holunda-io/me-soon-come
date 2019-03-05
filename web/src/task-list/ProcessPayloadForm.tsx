import * as React from "react";
import {createStyles, Theme, withStyles} from "@material-ui/core";
import {TaskItem} from "../shared/interfaces";
import TaskRowTextField from "./TaskRowTextField";

export interface BusinessDataFormProps {
  taskItem: TaskItem;
  classes: any;
}

export interface BusinessDataFormState {
  open: boolean;
}

const styles = (theme: Theme) =>
  createStyles({
    // form: {
    //   display: 'flex',
    //   flexDirection: 'column',
    //   margin: 'auto',
    //   width: 'fit-content',
    // },
    // formControl: {
    //   marginTop: theme.spacing.unit * 2,
    //   minWidth: 120,
    // },
    // formControlLabel: {
    //   marginTop: theme.spacing.unit,
    // },
  });


class ProcessPayloadForm extends React.Component<BusinessDataFormProps, BusinessDataFormState> {
  constructor(props: BusinessDataFormProps) {
    super(props);
    this.state = {
      open: false,
    };
  }


  render() {
    const {classes} = this.props;

    var payload = this.props.taskItem.task ? this.props.taskItem.task.payload : null;

    return (
      <form>
        <TaskRowTextField
          defaulValue={payload ? payload.request : null}
          label={"Request"}
          multiline={false}/>
        <TaskRowTextField
          defaulValue={payload ? payload.originator : null}
          label={"Originator"}
          multiline={false}/>

      </form>
    );
  }
}


export default withStyles(styles)(ProcessPayloadForm);
