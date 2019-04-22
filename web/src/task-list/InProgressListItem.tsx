import * as React from "react";
import { Task, ServerSentTaskEvent } from "../shared/interfaces";
import { Typography, Card, CardContent, CardActions, Button } from "@material-ui/core";

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
        card: {
            minWidth: 275,
          },
          bullet: {
            display: 'inline-block',
            margin: '0 2px',
            transform: 'scale(0.8)',
          },
          title: {
            fontSize: 14,
          },
          pos: {
            marginBottom: 12,
          },
    });
const date_format: string = "DD.MM.YYYY";

class InProgressListItem extends React.Component<ITaskListItemProps, ITaskListItemState> {
    constructor(props: ITaskListItemProps) {
        super(props);
        this.state = {

        };
    }


    completeTask = (taskEvent: ServerSentTaskEvent) => (event: any) => {

        let id: string = taskEvent.id;

        axios({
            method: 'post',
            url: 'http://localhost:8080/task/' + id + '/complete'

        }).catch(function (error) {
            console.log(error);
        });
    }
    unclaimTask = (taskEvent: ServerSentTaskEvent) => (event: any) => {

        let id: string = taskEvent.id;
        console.info("remove:" + id);

        axios({
            method: 'post',
            url: 'http://localhost:8080/task/' + id + '/unclaim'

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
                    <Button size="small" onClick={this.unclaimTask(this.props.taskEvent)}>unclaim</Button>
                    <Button size="small" onClick={this.completeTask(this.props.taskEvent)}>complete</Button>
                     </CardActions>
            </Card>

        );
    }

}

export default withStyles(styles)(InProgressListItem);