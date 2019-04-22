import * as React from "react";
import { Task, ServerSentTaskEvent } from "../shared/interfaces";
import { Typography, CardContent, Card } from "@material-ui/core";

import moment from "moment";
import { createStyles, withStyles } from "@material-ui/core/styles";
import { Theme } from "@material-ui/core/es";


export interface ITaskListItemProps {
    taskEvent: ServerSentTaskEvent;
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

class DoneListItem extends React.Component<ITaskListItemProps, ITaskListItemState> {
    constructor(props: ITaskListItemProps) {
        super(props);
        this.state = {

        };
    }

    handleClick = (taskEvent: ServerSentTaskEvent) => (event: any) => {


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
            </Card>
        );
    }

}

export default withStyles(styles)(DoneListItem);