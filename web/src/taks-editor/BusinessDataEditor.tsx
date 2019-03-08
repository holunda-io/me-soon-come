import * as React from "react";

import { createStyles, withStyles } from "@material-ui/core/styles";
import { Theme } from "@material-ui/core/styles/createMuiTheme";

import {Card, CardHeader, Avatar, IconButton, CardContent, Typography, CardActions, Collapse} from "@material-ui/core";

import FavoriteIcon from '@material-ui/icons/Favorite';
import ShareIcon from '@material-ui/icons/Share';
import ExpandMoreIcon from '@material-ui/icons/ExpandMore';
import MoreVertIcon from '@material-ui/icons/MoreVert';
import classnames from "classnames";
import TaskRowTextField from "../task-list/TaskRowTextField";

export interface IBusinessDataEditorProps {
    dataEntry: any;
    classes: any;
}

export interface IBusinessDataEditorState {
    expanded: boolean;
}

const styles = (theme: Theme) =>
    createStyles({

    });


const date_format: string = "DD.MM.YYYY";
class BusinessDataEditor extends React.Component<IBusinessDataEditorProps, IBusinessDataEditorState> {
    constructor(props: IBusinessDataEditorProps) {
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
        var payload = this.props.dataEntry.payload;

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
                    title="{this.props.task.name}"
                    subheader="{moment(this.props.task.createTime).format(date_format)}"
                />
                <CardContent>
                    <TaskRowTextField
                        defaulValue={payload ? payload.amount : null}
                        label={"Amount"}
                        multiline={false} />
                    <TaskRowTextField
                        defaulValue={payload ? payload.currency : null}
                        label={"Currency"}
                        multiline={false} />
                    <TaskRowTextField
                        defaulValue={payload ? payload.id : null}
                        label={"Id"}
                        multiline={false} />
                    <TaskRowTextField
                        defaulValue={payload ? payload.subject : null}
                        label={"Subject"}
                        multiline={false} />
                    <TaskRowTextField
                        defaulValue={payload ? payload.applicant : null}
                        label={"Applicant"}
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

export default withStyles(styles)(BusinessDataEditor);
