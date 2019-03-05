import * as React from "react";
import { TaskItem } from "../shared/interfaces";
import { ListItem, ListItemText, Typography, ListItemSecondaryAction, Checkbox } from "@material-ui/core";

import moment from "moment";
import { createStyles, withStyles } from "@material-ui/core/styles";
import { Theme } from "@material-ui/core/es";



export interface ITaskListItemProps {
    taskItem: TaskItem;
    classes: any;
    handleClick: any;
}

export interface ITaskListItemState {
}
const styles = (theme: Theme) =>
    createStyles({
        thead: {
            fontSize: "1.2rem"
        },
    });
const date_format: string = "DD.MM.YYYY";

class TaskListItem extends React.Component<ITaskListItemProps, ITaskListItemState> {
    constructor(props: ITaskListItemProps) {
        super(props);
        this.state = {

        };
    }
    public render() {

        const { classes } = this.props;

        return (
            <ListItem key="7"
                onClick={this.props.handleClick(this.props.taskItem)}
                button>
                <ListItemText
                    primary={this.props.taskItem.task.name}
                    secondary={
                        <React.Fragment>
                            <Typography component="span" className={classes.inline} color="textPrimary">
                                {moment(this.props.taskItem.task.createTime).format(date_format)}
                            </Typography>
                            {this.props.taskItem.task.description}
                        </React.Fragment>
                    }
                /><ListItemSecondaryAction>
                    <Checkbox

                    />
                </ListItemSecondaryAction>
            </ListItem>
        );
    }
   
}

export default withStyles(styles)(TaskListItem);