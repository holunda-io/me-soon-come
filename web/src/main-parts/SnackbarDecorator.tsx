import * as React from "react";
import CloseIcon from '@material-ui/icons/Close';

import green from '@material-ui/core/colors/green';
import amber from '@material-ui/core/colors/amber';
import { createStyles, withStyles } from "@material-ui/core/styles";
import { Theme } from "@material-ui/core/styles/createMuiTheme";
import { Snackbar, SnackbarContent, Icon, IconButton } from "@material-ui/core";
import classNames from "classnames";

export interface ISnackbarDecoratorProps {
    classes: any;
    open:boolean;
    message:string;
    variant: 'success'| 'warning'| 'error'| 'info';
}

export interface ISnackbarDecoratorState {
    open: boolean;
}

const styles = (theme: Theme) =>
    createStyles({
        success: {
            backgroundColor: green[600],
        },
        error: {
            backgroundColor: theme.palette.error.dark,
        },
        info: {
            backgroundColor: theme.palette.primary.dark,
        },
        warning: {
            backgroundColor: amber[700],
        },
        icon: {
            fontSize: 20,
        },
        iconVariant: {
            opacity: 0.9,
            marginRight: theme.spacing.unit,
        },
        message: {
            display: 'flex',
            alignItems: 'center',
        },
    });


class SnackbarDecorator extends React.Component<ISnackbarDecoratorProps, ISnackbarDecoratorState> {
    constructor(props: ISnackbarDecoratorProps) {
        super(props);
        this.state = {
            open: true
        };
    }

    componentWillReceiveProps = (props: ISnackbarDecoratorProps) => {
        this.setState({ open: props.open })
    }
    
    handleClose = (event: any) => {
        this.setState({ open: false });

        console.log(this.state);
    };

    public render() {
        const { classes, variant, ...other } = this.props;
        return (

            <Snackbar
                anchorOrigin={{
                    vertical: 'bottom',
                    horizontal: 'left',
                }}
                open={this.state.open}
                onClose={this.handleClose}
                autoHideDuration={6000}
            >
                <SnackbarContent
                    className={classNames(classes[variant])}
                    aria-describedby="client-snackbar"
                    message={
                        <span id="client-snackbar" className={classes.message}>
                            <Icon className={classNames(classes.icon, classes.iconVariant)} />
                            {this.props.message}
                        </span>
                    }
                    action={[
                        <IconButton
                            key="close"
                            aria-label="Close"
                            color="inherit"
                            className={classes.close}
                            onClick={this.handleClose}
                        >
                            <CloseIcon className={classes.icon} />
                        </IconButton>,
                    ]}
                    {...other}
                />
            </Snackbar>


        );
    }
}

export default withStyles(styles)(SnackbarDecorator);
