import * as React from "react";

import { createStyles, withStyles } from "@material-ui/core/styles";
import { Theme } from "@material-ui/core/styles/createMuiTheme";

import { TaskItem, Task } from "../shared/interfaces";
import { Grid, Card, CardHeader, Avatar, IconButton, CardMedia, CardContent, Typography, CardActions, Collapse, TextField } from "@material-ui/core";

import moment from "moment";
import FavoriteIcon from '@material-ui/icons/Favorite';
import ShareIcon from '@material-ui/icons/Share';
import ExpandMoreIcon from '@material-ui/icons/ExpandMore';
import MoreVertIcon from '@material-ui/icons/MoreVert';
import classnames from "classnames";
import TaskRowTextField from "../task-list/TaskRowTextField";
import TaskRowTextListField from "../task-list/TaskRowTextListField";


export interface ITaskEditorProps {
    task: Task;
    id: string;
    classes: any;
}

export interface ITaskEditorState {
    expanded: boolean;
}

const styles = (theme: Theme) =>
    createStyles({

    });


const date_format: string = "DD.MM.YYYY";
class TaskEditor extends React.Component<ITaskEditorProps, ITaskEditorState> {
    constructor(props: ITaskEditorProps) {
        super(props);
        this.state = {
            expanded: false
        };
    }
    handleExpandClick = () => {
        this.setState(state => ({ expanded: !state.expanded }));
    };
    public render() {
        const { classes } = this.props;
        var task = this.props.task;

        return (

            <Card className={classes.card}>
                <CardHeader
                    avatar={
                        <Avatar aria-label="Recipe" className={classes.avatar}>
                            R
            </Avatar>
                    }
                    action={
                        <IconButton>
                            <MoreVertIcon />
                        </IconButton>
                    }
                    title={this.props.task.name}
                    subheader={moment(this.props.task.createTime).format(date_format)}
                />
                <CardContent>
                    <TextField
                        disabled
                        id="standard-disabled"
                        label="id"
                        defaultValue={this.props.task.name}
                        className={this.props.classes.textField}
                        margin="normal"
                        multiline={true}
                    />
                    <TaskRowTextField
                        defaulValue={this.props.task.assignee}
                        label={"assignee"}
                        multiline={false} />
                    <TaskRowTextField
                        defaulValue={this.props.task.id}
                        label={"id"}
                        multiline={false} />
                    <TaskRowTextField
                        defaulValue={this.props.task.businessKey}
                        label={"businessKey"}
                        multiline={false} />
                    <TaskRowTextListField
                        defaulValue={this.props.task.candidateGroups}
                        label={"candidateGroups"} />
                    <TaskRowTextListField
                        defaulValue={this.props.task.candidateUsers}
                        label={"candidateUsers"} />
                    <TaskRowTextField
                        defaulValue={moment(this.props.task.followUpDate).format(date_format)}
                        label={"followUpDate"}
                        multiline={false} />
                    <TaskRowTextField
                        defaulValue={this.props.task.formKey}
                        label={"formKey"}
                        multiline={false} />
                    <TaskRowTextField
                        defaulValue={this.props.task.assignee}
                        label={"assignee"}
                        multiline={false} />
                </CardContent>
                <CardActions className={classes.actions} disableActionSpacing>
                    <IconButton aria-label="Add to favorites">
                        <FavoriteIcon />
                    </IconButton>
                    <IconButton aria-label="Share">
                        <ShareIcon />
                    </IconButton>
                    <IconButton
                        className={classnames(classes.expand, {
                            [classes.expandOpen]: this.state.expanded,
                        })}
                        onClick={this.handleExpandClick}
                        aria-expanded={this.state.expanded}
                        aria-label="Show more"
                    >
                        <ExpandMoreIcon />
                    </IconButton>
                </CardActions>
                <Collapse in={this.state.expanded} timeout="auto" unmountOnExit>
                    <CardContent>
                        <Typography paragraph>Method:</Typography>
                        <Typography paragraph>
                            Heat 1/2 cup of the broth in a pot until simmering, add saffron and set aside for 10
                            minutes.
            </Typography>
                        <Typography paragraph>
                            Heat oil in a (14- to 16-inch) paella pan or a large, deep skillet over medium-high
                            heat. Add chicken, shrimp and chorizo, and cook, stirring occasionally until lightly
                            browned, 6 to 8 minutes. Transfer shrimp to a large plate and set aside, leaving
                            chicken and chorizo in the pan. Add pimentón, bay leaves, garlic, tomatoes, onion,
                            salt and pepper, and cook, stirring often until thickened and fragrant, about 10
                            minutes. Add saffron broth and remaining 4 1/2 cups chicken broth; bring to a boil.
            </Typography>
                        <Typography paragraph>
                            Add rice and stir very gently to distribute. Top with artichokes and peppers, and cook
                            without stirring, until most of the liquid is absorbed, 15 to 18 minutes. Reduce heat
                            to medium-low, add reserved shrimp and mussels, tucking them down into the rice, and
                            cook again without stirring, until mussels have opened and rice is just tender, 5 to 7
                            minutes more. (Discard any mussels that don’t open.)
            </Typography>
                        <Typography>
                            Set aside off of the heat to let rest for 10 minutes, and then serve.
            </Typography>
                    </CardContent>
                </Collapse>
            </Card>
        );
    }
}

export default withStyles(styles)(TaskEditor);
