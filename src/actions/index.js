import axios from 'axios';
require("babel-polyfill");
export const PROJECT_SELECTED = 'PROJECT_SELECTED';

// Action Creator
export const selectProject = (name) => {

    return async function(dispatch, getState)
    {
        let project = null;
        await axios.get('../../data/projects_pl.json').then(response => {
            if(response.data.projects)
                project = response.data.projects.find(o=>o.tag === name);
        })
            .catch(error => {
                console.log(error)
            });

        // Return an action
        dispatch({
            type: PROJECT_SELECTED,
            payload: project
        })
    };
};
