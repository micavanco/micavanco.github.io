import React, {Component} from 'react';
import { Route, HashRouter} from "react-router-dom";


import MainPage from './MainPage';
import Projects from './Projects';

class App extends Component{
    render() {
        return(
        <div className="reset">
            <HashRouter>
                <div className="reset">
                    <Route exact path="/" component={MainPage} />
                    <Route path="/projects" component={Projects} />
                </div>
            </HashRouter>
        </div>);
    }
}

export default App;
