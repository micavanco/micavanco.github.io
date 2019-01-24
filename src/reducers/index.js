import { combineReducers } from 'redux';
import { PROJECT_SELECTED } from '../actions';


const projectReducer = (selectedProject = null, action)=>{
    switch(action.type){
        case PROJECT_SELECTED:
            if(action.payload){
                return action.payload;
            }
            return null;
        default: return selectedProject;
    }
};

export default combineReducers({
    project: projectReducer
});

