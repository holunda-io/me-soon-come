import * as React from "react";

import { createStyles, withStyles } from "@material-ui/core/styles";
import { Theme } from "@material-ui/core/styles/createMuiTheme";
import { Dialog, DialogTitle, DialogContent, DialogContentText, DialogActions, Button, TextField } from "@material-ui/core";
import Axios from "axios";

import classNames from "classnames";

export interface INewRequestDialogProps {
    classes: any;
    open: boolean;
}

export interface INewRequest {
    amount: number;
    applicant: string;
    currency: string;
    subject: string;
}
export interface INewRequestDialogState {
    open: boolean;
    newRequest: INewRequest;
}

const styles = (theme: Theme) =>
    createStyles({

    });


const date_format: string = "DD.MM.YYYY";
class NewRequestDialog extends React.Component<INewRequestDialogProps, INewRequestDialogState> {
    constructor(props: INewRequestDialogProps) {
        super(props);
        this.state = {
            open: false,
            newRequest: {
                amount: 0,
                applicant: "Kermit",
                currency: "EUR",
                subject: "Subject, what's dis?"
            }
        };
    }
    componentWillReceiveProps = (props: INewRequestDialogProps) => {
        this.setState({ open: props.open })
    }

    handleCreateNewRequest = (event: any) => {

        Axios.post('http://localhost:8080//example-process-approval/rest/request', this.state.newRequest)
            .then(res => {
                console.log('Created new request with id x: ', res.data);
                this.setState({
                    open: false
                })
            })
            .catch(err => {
                console.error(err);
                this.setState({
                    open: true
                })
            })
    };

    handleClose = (event: any) => {
        this.setState({ open: false });

        console.log(this.state);
    };


    handleChange = (name: string) => (event: any) => {
        let req = this.state.newRequest;
        switch (name) {
            case "amount": {
                req.amount = event.target.value;
                break;
            }
            case "currency": {
                req.currency = event.target.value;
                break;
            }
            default: {
                //statements; 
                break;
            }
        }
        this.setState({
            newRequest: req
        })
    };

    public render() {
        const { classes } = this.props;

        return (

            <Dialog open={this.state.open} onClose={this.handleClose} aria-labelledby="form-dialog-title">
                <DialogTitle id="form-dialog-title">Subscribe</DialogTitle>
                <DialogContent>
                    <DialogContentText>
                        To subscribe to this website, please enter your email address here. We will send updates
                        occasionally.
              </DialogContentText>
                    <TextField
                        id="amount"
                        label="Amount"
                        className={classNames(classes.textField, classes.dense)}
                        margin="dense"
                        value={this.state.newRequest.amount}
                        onChange={this.handleChange('amount')}
                    />
                    <TextField
                        id="currency"
                        label="Currency"
                        className={classNames(classes.textField, classes.dense)}
                        margin="dense"
                        value={this.state.newRequest.currency}
                        onChange={this.handleChange('currency')}
                    />


                </DialogContent>

                <DialogActions>
                    <Button onClick={this.handleClose} color="primary">
                        Cancel
                    </Button>
                    <Button onClick={this.handleCreateNewRequest} color="primary">
                        Add
                    </Button>
                </DialogActions>
            </Dialog>

        );
    }
}

export default withStyles(styles)(NewRequestDialog);
