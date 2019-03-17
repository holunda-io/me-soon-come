import React, { Component } from 'react';
import Select from 'react-select'
import { Theme } from "@material-ui/core/styles/createMuiTheme";
import { createStyles, withStyles } from "@material-ui/core/styles";
import axios from 'axios';

const options = [
  { value: 'chocolate', label: 'Chocolate' },
  { value: 'strawberry', label: 'Strawberry' },
  { value: 'vanilla', label: 'Vanilla' }
]
export interface OptionValue {
  value: string;
  label: string;
}
export interface IAutocompleteProps {
  classes: any
}
export interface IAutocompleteState {
  options: OptionValue[]
}

const styles = (theme: Theme) =>
  createStyles({

  });
class Autocomplete extends Component<IAutocompleteProps, IAutocompleteState> {
  constructor(props: IAutocompleteProps) {
    super(props);
    this.state = {
      options: []
    };
  }

  componentDidMount() {
    axios.get('http://localhost:8080/admin/api/properties').then(res => {
      console.log(res.data);
      let d: string[] = res.data;
      let o: OptionValue[] = d.map(obj => {
        var rObj: OptionValue = { label: obj, value: obj };
        return rObj;
      });
      this.setState({
        options: o
      });
    })
  }
  render() {
    return (
      <Select options={this.state.options} />
    );
  }
}

export default withStyles(styles)(Autocomplete);