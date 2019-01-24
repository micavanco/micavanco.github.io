import React, {Component} from 'react';

import MenuMain from './MenuMain';
import Project from './ProjectWindow'


class Projects extends Component{

    constructor(params)
    {
        super(params);

        this.state = {
            current_project: "movement_analyzer"
        };

    }


    render() {

        return(
            <div className="content">
                {/* Header section */}
                <header className="header">
                    <div className="logo-container">
                        <a href="" className="logo">Olech</a>
                    </div>
                    <img src="../../img/britainFlag.png" id="menu-flag-b"/>
                    <img src="../../img/polishFlag.png" id="menu-flag-p"/>
                    <a className="menu-btn" id="menu-btn-projects" href="projects">PROJEKTY</a>
                    <a className="menu-btn" id="menu-btn-about" href="/">O MNIE</a>
                </header>

                {/* Whole website content */}
                <div className="content-page">
                    <MenuMain/>
                    <Project/>
                </div>


                {/* Footer section */}
                <div className="footer">
                    <div className="logo-container" style={{float: "left"}}>
                        <a href="" className="logo">Olech</a>
                    </div>
                    <div className="footer-container">
                        <div className="footer-container-contact">
                            <p>E-mail:</p><p style={{float: "right", fontWeight: 600}}>michal.olech.mail@gmail.com</p>
                            <p style={{clear: "left"}}>Telefon:</p><p style={{float: "right", fontWeight: 600}}>513514703</p>
                        </div>
                        <div id="footer-copy">&copy; 2019 Michał Olech</div>
                    </div>
                </div>


            </div>
        );
    }

}

export default Projects;

