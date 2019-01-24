import React, {Component} from 'react';
import styled from 'styled-components';

let bird1 = {x: 0, name: "bird1"};
let bird2 = {x: 0, name: "bird2"};
let bird3 = {x: 0, name: "bird3"};
let bird4 = {x: 0, name: "bird4"};
let bird5 = {x: 0, name: "bird5"};
let bird1flip = {x: 0, name: "bird1flip"};
let bird2flip = {x: 0, name: "bird2flip"};
let bird3flip = {x: 0, name: "bird3flip"};
let bird4flip = {x: 0, name: "bird4flip"};
let bird5flip = {x: 0, name: "bird5flip"};

let current_number = 0;
let current_number2 = 3;

let score = 0;
let highscore = 0;
let time = 180;

const birds_tab_left = [bird1, bird2, bird3, bird4, bird5];
const birds_tab_right =  [bird1flip, bird2flip, bird3flip, bird4flip, bird5flip];

class HuntingGame extends Component{

    constructor(params)
    {
        super(params);

        this.state = {
            interval1: null,
            interval2: null,
            gameStatus: false,
            timer: null,
            score: 0
        };

        document.addEventListener("keydown", (e)=>{
            if(e.keyCode == 81 && this.state.gameStatus)
            {
                document.getElementById("score").innerText = "0";
                clearInterval(this.state.interval1);
                clearInterval(this.state.interval2);
                clearInterval(this.state.timer);

                if(highscore < score)
                    highscore = score;

                this.setState({gameStatus: false});
                document.getElementById("menu-game").style.display = "flex";
                document.getElementById("menu-game").innerText = "Kliknij aby rozpocząć grę\nNajwyższy wynik: "+
                    highscore;
            }
        });
    }

    onClickStartGame()
    {

        if(!this.state.gameStatus)
        {
            document.getElementById("menu-game").style.display = "none";
            document.getElementById("game-time").innerText = "3:00";
            score = 0;
            time = 180;
            this.setState({gameStatus: true,
                interval1: setInterval(this.flyToRight, 3100),
                interval2: setInterval(this.flyToLeft, 3100),
                timer: setInterval(this.onTimer.bind(this), 1000)
            });
        }
    }

    render() {

        const Game = styled.div`
          overflow: hidden;
          float: left;
        `;

        const Bird1 = styled.div`
            background: url("../../img/bird1.png") no-repeat;
            position: relative;
            display: none;
            width: 50px;
            height: 42px;
            left: 417px;
            top: 0px;
        `;
        const Bird2 = styled.div`
            background: url("../../img/bird2.png") no-repeat;
            position: relative;
            width: 70px;
            display: none;
            height: 59px;
            left: 417px;
            top: 10px;
        `;
        const Bird3 = styled.div`
            background: url("../../img/bird3.png") no-repeat;
            position: relative;
            width: 65px;
            height: 63px;
            display: none;
            left: 417px;
            top: 20px;
        `;
        const Bird4 = styled.div`
            background: url("../../img/bird4.png") no-repeat;
            position: relative;
            width: 38px;
            height: 32px;
            left: 417px;
            display: none;
            top: 30px;
        `;
        const Bird5 = styled.div`
            background: url("../../img/bird5.png") no-repeat;
            position: relative;
            width: 38px;
            height: 32px;
            left: 417px;
            display: none;
            top: 30px;
        `;

        const Bird1flip = styled.div`
            background: url("../../img/bird1flip.png") no-repeat;
            position: relative;
            width: 50px;
            height: 42px;
            left: -71px;
            top: 30px;
        `;
        const Bird2flip = styled.div`
            background: url("../../img/bird2flip.png") no-repeat;
            position: relative;
            width: 70px;
            height: 59px;
            left: -71px;
            top: 30px;
        `;
        const Bird3flip = styled.div`
            background: url("../../img/bird3flip.png") no-repeat;
            position: relative;
            width: 65px;
            height: 63px;
            left: -71px;
            top: 30px;
        `;
        const Bird4flip = styled.div`
            background: url("../../img/bird4flip.png") no-repeat;
            position: relative;
            width: 38px;
            height: 32px;
            left: -71px;
            top: 30px;
        `;
        const Bird5flip = styled.div`
            background: url("../../img/bird5flip.png") no-repeat;
            position: relative;
            width: 38px;
            height: 32px;
            left: -71px;
            top: 30px;
        `;
        let Menu = null;
        if(!this.state.gameStatus)
        Menu = styled.div`
            width: inherit;
            height: inherit;
            position: absolute;
            background: rgba(254, 254, 254, 0.95);
            display: flex;
            justify-content: center;
            align-items: center;
            font-size: 18px;
            font-weight: 600;
            z-index: 9;
        `;
        else
            Menu = styled.div`
            display: none;
        `;


        const Score = styled.div`
            position: absolute;
            height: 20px;
            width: inherit;
            z-index: 8;
        `;

        return(
            <div className="img-box-left" onClick={this.onClickStartGame.bind(this)} id="img-box-left">
                <Menu id="menu-game">Kliknij aby rozpocząć grę</Menu>
                <Score>Liczba punktów: <span id="score">0</span><span id="game-time">3:00</span><span id="score-info">Wciśnij q by zakończyć</span></Score>
                <Game src="../../img/birds-land.png" id="birds-land" className="game">
                    <Bird1flip id="bird1flip" onClick={this.onBirdClick.bind(this)}/>
                    <Bird2flip id="bird2flip" onClick={this.onBirdClick.bind(this)}/>
                    <Bird3flip id="bird3flip" onClick={this.onBirdClick.bind(this)}/>
                    <Bird4flip id="bird4flip" onClick={this.onBirdClick.bind(this)}/>
                    <Bird5flip id="bird5flip" onClick={this.onBirdClick.bind(this)}/>
                    <Bird1 id="bird1" onClick={this.onBirdClick.bind(this)}/>
                    <Bird2 id="bird2" onClick={this.onBirdClick.bind(this)}/>
                    <Bird3 id="bird3" onClick={this.onBirdClick.bind(this)}/>
                    <Bird4 id="bird4" onClick={this.onBirdClick.bind(this)}/>
                    <Bird5 id="bird5" onClick={this.onBirdClick.bind(this)}/>
                </Game>
            </div>
        );
    }



    flyToRight(){

        let bird = birds_tab_right[current_number];
        let el = document.getElementById(bird.name);

        if(bird.x === -71)
        {
            el.style.display = "block";
            el.style.top = (Math.floor(Math.random() * 450)+30).toString()+"px";
            bird.x = -70;
        }
        else if(bird.x < 417 && bird.x > -71)
            bird.x+=500;
        else
        {
            el.style.display = "none";
            bird.x=-71;
            current_number = Math.floor(Math.random()*5);
        }
        el.style.left = bird.x.toString()+"px";
    }

    flyToLeft(){

        let bird = birds_tab_left[current_number];
        let el = document.getElementById(bird.name);

        if(bird.x === 418)
        {
            el.style.display = "block";
            el.style.top = (Math.floor(Math.random() * 400)+30).toString()+"px";
            bird.x = 417;
        }
        else if(bird.x > 0 && bird.x < 418)
            bird.x-=560;
        else
        {
            el.style.display = "none";
            bird.x = 418;
            current_number2 = Math.floor(Math.random()*5);
        }
        el.style.left = bird.x.toString()+"px";
    }

    onTimer()
    {
        time--;
        let min = Math.floor(time/60);
        if(min < 0) min=0;
        let sec = time-min*60;
        if(sec < 10)
            document.getElementById("game-time").innerText = min+":0"+sec;
        else
            document.getElementById("game-time").innerText = min+":"+sec;
        if(time === 0)
        {
            clearInterval(this.state.timer);
            document.getElementById("score").innerText = "0";
            clearInterval(this.state.interval1);
            clearInterval(this.state.interval2);

            if(highscore < score)
                highscore = score;

            this.setState({gameStatus: false});
            document.getElementById("menu-game").style.display = "flex";
            document.getElementById("menu-game").innerText = "Kliknij aby rozpocząć grę\nNajwyższy wynik: "+
                highscore;
        }
    }

    onBirdClick(e)
    {
        if(this.state.gameStatus)
        {
            if(e.target.id === "bird4flip" || e.target.id === "bird4" ||
                e.target.id === "bird5flip" || e.target.id === "bird5")
                score+=4;
            else
                score++;
            document.getElementById("score").innerText = score;
            document.getElementById(e.target.id).style.display = "none";
        }

    }

}

export default HuntingGame;
