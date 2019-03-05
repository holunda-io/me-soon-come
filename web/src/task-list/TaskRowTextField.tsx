import * as React from "react";
import TextField from "@material-ui/core/es/TextField/TextField";
import {createStyles, Theme, withStyles} from "@material-ui/core";

interface ITextFieldProps {
  label: string;
  defaulValue: string;
  multiline:boolean;
  classes: any;
}

const styles = (theme: Theme) =>
  createStyles({
    textField: {
      marginLeft: theme.spacing.unit,
      marginRight: theme.spacing.unit,
      width: 200,
    },
  });

class TaskRowTextField extends React.Component<ITextFieldProps> {
  constructor(props: any) {
    super(props);
    this.state = {};
  }

  public render() {

    const {classes} = this.props;

    var defaultValue = this.props.defaulValue ? this.props.defaulValue : "";
    return (
      <TextField
        disabled
        id="standard-disabled"
        label={this.props.label}
        value={defaultValue}
        className={this.props.classes.textField}
        margin="normal"
        multiline={this.props.multiline}
      />
    )
  }

}

export default withStyles(styles)(TaskRowTextField);
