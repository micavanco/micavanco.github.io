import React, {Component} from 'react';
import { BrowserRouter, Route} from "react-router-dom";

import MainPage from './MainPage';
import Projects from './Projects';

class App extends Component{
    render() {
        return(
        <div className="reset">
            <BrowserRouter>
                <div className="reset">
                    <Route path="/" exact component={MainPage} />
                    <Route path="/projects" component={Projects} />
                </div>
            </BrowserRouter>
        </div>);
    }
}

export default App;
