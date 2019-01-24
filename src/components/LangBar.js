import React, {Component} from 'react';
import styled from 'styled-components';


class LangBar extends Component{

    constructor(params)
    {
        super(params);

    }




    render() {



        const ProgressBarFront = styled.div`
            border: 1px solid black;
            width: ${this.props.width}px;
            height: 33px;
            position: absolute;
            background: rgba(0, 158, 217, 0.6);
            color: rgba(0, 158, 217, 0.6);
            right: 0;
        `;

        const ProgressBarBack = styled.div`
            border: 1px solid black;
            width: ${this.props.width}px;
            height: 33px;
            position: absolute;
            background: rgba(0, 158, 217, 0.6);
            color: rgba(0, 158, 217, 0.6);
            right: 0;
            margin: 1px 0 0 10px;
        `;

        const ProgressBarBottom = styled.div`
            border: 1px solid black;
            width: ${this.props.width}px;
            height: 33px;
            position: absolute;
            background: rgba(0, 158, 217, 0.6);
            color: rgba(0, 158, 217, 0.6);
            right: 0;
            height: inherit;
        `;

        const ProgressBarTop = styled.div`
            border: 1px solid black;
            width: ${this.props.width}px;
            height: 15px;
            position: absolute;
            background: rgba(0, 158, 217, 0.6);
            color: rgba(0, 158, 217, 0.6);
            right: 0;
        `;


        return(
            <div className="lang-bar-content" >

                <div className="lang-bar lang-bar-front" >
                    <ProgressBarFront id={this.props.lang+1}/>
                </div>

                <div className="lang-bar lang-bar-back">
                    <ProgressBarBack id={this.props.lang+2}/>
                </div>

                <div className="lang-bar lang-bar-bottom">
                    <ProgressBarBottom id={this.props.lang+3}/>
                </div>

                <div className="lang-bar lang-bar-top">
                    <ProgressBarTop id={this.props.lang+4}/>
                </div>

            </div>
        );
    }

    componentDidMount()
    {
        let el = document.getElementById(this.props.lang+1);
        let el1 = document.getElementById(this.props.lang+2);
        let el2 = document.getElementById(this.props.lang+3);
        let el3 = document.getElementById(this.props.lang+4);
        let tab = [el, el1, el2, el3];

        if(this.props.width<30)tab.forEach(e=>{
            e.style.background="#f44b42"
        });
        else if(this.props.width>=30 && this.props.width<40)tab.forEach(e=>{
            e.style.background="#f4a04d"
        });
        else if(this.props.width>=40 && this.props.width<60)tab.forEach(e=>{
            e.style.background="#f0f451"
        });
        else if(this.props.width>=60 && this.props.width<90)tab.forEach(e=>{
            e.style.background="#aaf4c9"
        });
        else if(this.props.width>=90 && this.props.width<120)tab.forEach(e=>{
            e.style.background="#94f4f4"
        });
        else if(this.props.width>=120 && this.props.width<130)
            tab.forEach(e=>{
                e.style.background="#7fc2f4"
            });
        else
            tab.forEach(e=>{
                e.style.background="#5694f4"
            });


        document.getElementById(this.props.lang).addEventListener("mouseenter", ()=>{

            tab.forEach(e=>{
                e.style.width="0px";
                e.classList.remove("lang-bar-transition");
            });

            setTimeout(()=>{
                tab.forEach(e=>{
                    e.style.display="inline";
                    e.classList.add("lang-bar-transition");
                    e.style.width=this.props.width+"px";
                });
            }, 50);
        });
    }
}

export default LangBar;
