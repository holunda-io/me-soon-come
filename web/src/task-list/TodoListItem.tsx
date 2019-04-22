import * as React from "react";
import { Task, ServerSentTaskEvent } from "../shared/interfaces";
import { Typography, Button, Card, CardContent, CardActions } from "@material-ui/core";

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

class NewTaskListItem extends React.Component<ITaskListItemProps, ITaskListItemState> {
    constructor(props: ITaskListItemProps) {
        super(props);
        this.state = {

        };
    }


    setAssignee = (taskEvent: ServerSentTaskEvent) => (event: any) => {

        let id: string = taskEvent.id;
        let assignee = { "userId": "Horst" };

        axios({
            method: 'post',
            url: 'http://localhost:8080/task/' + id + '/assignee',
            data:
                assignee

        }).catch(function (error) {
            console.log(error);
        });
    }

    setCandidateUser = (taskEvent: ServerSentTaskEvent) => (event: any) => {

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
            <Card className={classes.card}>
                <CardContent>
                    <Typography component="span" className={classes.inline} color="textPrimary">
                        {moment(task.createTime).format(date_format)}
                    </Typography>
                    {task.description}

                </CardContent>
                <CardActions>
                    <Button size="small" onClick={this.setAssignee(this.props.taskEvent)}>setAssignee</Button>
                </CardActions>
            </Card>

        );
    }

}

export default withStyles(styles)(NewTaskListItem);