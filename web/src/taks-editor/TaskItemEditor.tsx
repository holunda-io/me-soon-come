import * as React from "react";

import { createStyles, withStyles } from "@material-ui/core/styles";
import { Theme } from "@material-ui/core/styles/createMuiTheme";

import { TaskItem, Task } from "../shared/interfaces";
import { Grid } from "@material-ui/core";
import { Paper, Divider } from "@material-ui/core/es";
import TaskEditor from "./TaskEditor";
import BusinessDataEditor from "./BusinessDataEditor";


export interface ITaskItemEditorProps {
    taskItem: TaskItem;
    classes: any;
}

export interface ITaskItemEditorState {
}

const styles = (theme: Theme) =>
    createStyles({

    });


class TaskItemEditor extends React.Component<ITaskItemEditorProps, ITaskItemEditorState> {
    constructor(props: ITaskItemEditorProps) {
        super(props);
        this.state = {
        };
    }

    public render() {
        const { classes } = this.props;

        return (
            <React.Fragment>
                <Grid
                    container
                    spacing={24}
                >
                    <Grid item xs={10}>
                        <TaskEditor task={this.props.taskItem.task} id={this.props.taskItem.task.id} />

                        {this.props.taskItem.dataEntries[0] ? (

                            <BusinessDataEditor dataEntry={this.props.taskItem.dataEntries[0]} />
                        ) : (

                                <Paper >select a task, please</Paper>
                            )}
                        <Paper square={true}>
                            {this.props.taskItem.task.description}
                        </Paper>
                    </Grid>
                </Grid>
            </React.Fragment>
        );
    }
}

export default withStyles(styles)(TaskItemEditor);
