import React, {Component} from 'react';
import {bindActionCreators} from "redux";
import {connect} from "react-redux";

import { selectProject } from "../actions";

let isInside = false;
let isInside2 = false;

class MenuMain extends Component{

    constructor(params)
    {
        super(params);

        isInside = false;

        this.state = {
            menu_options: null
        };

        this.props.selectProject("movementanalyzer");
    }

    render() {

        return(
            <div className="menu-container" onMouseLeave={this.onMouseContainerLeave}>
                {/*    ----- MODAL WINDOW ------      */}
                <div className="menu-container" id="menu-container"
                     onMouseEnter={()=>isInside=true}
                     onMouseLeave={this.onMouseContainerLeave}><div className="menu-header" id="menu-header">Projekty</div>
                    {this.state.menu_options}
                    <div id="menu-container-after"
                         onMouseEnter={()=>isInside=true}>
                    </div>
                </div>
                {/*    ----- STATIC WINDOW ------      */}
                <div className="menu-header">Projekty</div>
                <div className="menu-options">
                    <div className="menu-options-button" id="c-projects" onMouseEnter={this.onMouseOverElement.bind(this)}
                    onMouseLeave={this.onMouseLeaveElement}>
                        C++/C
                    </div>
                    <div className="menu-options-button" id="java-projects" onMouseEnter={this.onMouseOverElement.bind(this)}
                         onMouseLeave={this.onMouseLeaveElement}>
                        Java
                    </div>
                    <div className="menu-options-button" id="embedded-projects" onMouseEnter={this.onMouseOverElement.bind(this)}
                         onMouseLeave={this.onMouseLeaveElement}>
                        Embedded
                    </div>
                    <div className="menu-options-button" id="react-projects" onMouseEnter={this.onMouseOverElement.bind(this)}
                         onMouseLeave={this.onMouseLeaveElement}>
                        React
                    </div>
                </div>
            </div>);
    }

    onMouseOverElement(e)
    {
        document.getElementById("menu-container").style.display = "inline-block";
        document.getElementById("menu-container-after").style.display = "inline-block";
        isInside2 =true;
        if(e.target.id === "c-projects")
        {
            document.getElementById("menu-container-after").style.top = "60px";
            document.getElementById("menu-header").innerText = "C++/C";
            this.setState({menu_options:
                    <div className="menu-options">
                        <div className="menu-options-button2" id="movementanalyzer"
                        onClick={this.onProjectClick.bind(this)}>
                            Analizator Ruchu
                        </div>
                        <div className="menu-options-button2" id="snake3d"
                             onClick={this.onProjectClick.bind(this)}>
                            Snake3D
                        </div>
                        <div className="menu-options-button2" id="comcommunicator"
                             onClick={this.onProjectClick.bind(this)}>
                            COMCommunicator
                        </div>
                    </div>});
        }else if(e.target.id === "java-projects")
        {
            document.getElementById("menu-container-after").style.top = "108px";
            document.getElementById("menu-header").innerText = "Java";
            this.setState({menu_options:
                    <div className="menu-options">
                        <div className="menu-options-button2" id="analyzerbvh"
                             onClick={this.onProjectClick.bind(this)}>
                            Analizator BVH
                        </div>
                        <div className="menu-options-button2" id="norskmemo"
                             onClick={this.onProjectClick.bind(this)}>
                            NorskMemo
                        </div>
                        <div className="menu-options-button2" id="pagescheck"
                             onClick={this.onProjectClick.bind(this)}>
                            PagesCheck
                        </div>
                    </div>});
        }else if(e.target.id === "embedded-projects")
        {
            document.getElementById("menu-container-after").style.top = "155px";
            document.getElementById("menu-header").innerText = "Embedded";
            this.setState({menu_options:
                    <div className="menu-options">
                        <div className="menu-options-button2" id="assembler"
                             onClick={this.onProjectClick.bind(this)}>
                            Miernik Temperatury
                        </div>
                    </div>});
        }else if(e.target.id === "react-projects")
        {
            document.getElementById("menu-container-after").style.top = "205px";
            document.getElementById("menu-header").innerText = "React";
            this.setState({menu_options:
                    <div className="menu-options">
                        <div className="menu-options-button2" id="reactmovieapp"
                             onClick={this.onProjectClick.bind(this)}>
                            React Movie App
                        </div>
                        <div className="menu-options-button2" id="weatherapp"
                             onClick={this.onProjectClick.bind(this)}>
                            WeatherApp
                        </div>
                        <div className="menu-options-button2" id="portfolio">
                            Ta strona ;)
                        </div>
                    </div>});
        }
    }

    onMouseLeaveElement()
    {
        setTimeout(()=>{
            if(!isInside && !isInside2)document.getElementById("menu-container").style.display = "none";
            if(!isInside && !isInside2)document.getElementById("menu-container-after").style.display = "none";
        }, 50);
            isInside2 = false;
    }

    onMouseContainerLeave()
    {
        document.getElementById("menu-container").style.display = "none";
        document.getElementById("menu-container-after").style.display = "none";
        isInside = false;
        isInside2 = false;
    }

    onProjectClick(e)
    {
        this.props.selectProject(e.target.id);
    }

}

function mapDispatchToProps(dispatch) {

    return bindActionCreators({selectProject}, dispatch);
}

export default connect(null ,mapDispatchToProps)(MenuMain);
