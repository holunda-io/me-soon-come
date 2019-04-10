import * as React from "react";
import { Task, User, ServerSentTaskEvent } from "../shared/interfaces";
import { ListItem, ListItemText, Typography, ListItemSecondaryAction, Checkbox } from "@material-ui/core";

import moment from "moment";
import { createStyles, withStyles } from "@material-ui/core/styles";
import { Theme } from "@material-ui/core/es";

import axios from 'axios';



export interface ITaskListItemProps {
    taskEvent: ServerSentTaskEvent;
    classes: any;
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


    handleClick = (taskEvent: ServerSentTaskEvent) => (event: any) => {

        let id: string = taskEvent.id;
        let candidateUsers = [{ "userId": "Horst" }, { "userId": "Helga" }];

        axios({
            method: 'post',
            url: 'http://localhost:8080/task/' + id + '/candidateUsers',
            data:
                candidateUsers

        }).catch(function (error) {
            console.log(error);
        });
    }
    public render() {

        const { classes, taskEvent } = this.props;
        let task: Task = taskEvent.data;

        return (
            <ListItem 
                onClick={this.handleClick(this.props.taskEvent)}
                button>
                <ListItemText
                    primary={task.name}
                    secondary={
                        <React.Fragment>
                            <Typography component="span" className={classes.inline} color="textPrimary">
                                {moment(task.createTime).format(date_format)}
                            </Typography>
                            {task.description}
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