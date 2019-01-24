import React, {Component} from 'react';
import LangBar from './LangBar';
import HuntingGame from './HuntingGame';

let wasLoaded = false;

class MainPage extends Component{

    constructor(params)
    {
        super(params);

        wasLoaded = false;

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
                        <a className="menu-btn" id="menu-btn-about" href="">O MNIE</a>
                </header>

                {/* Whole website content */}
                <div className="content-page">
                    <div id="front-content">
                        <div id="front-text">
                            <h1>Szanowni Państwo</h1>
                            <h2>Zapraszam do zapoznania się z portfolio</h2>
                            <h3>Na niniejszej stronie znajdą Państwo wszelkie
                                informacje dotyczące mojego doświadczenia zawodowego oraz mojej osoby</h3>
                            <img src="../../img/signature.png" id="signature"/>
                        </div>
                        <img src="../../img/face.png" id="face"/>
                    </div>
                    <div className="info-box-left" id="info-box-left" >
                        <h1 className="info-box-left-head">O mnie</h1>
                        <h3 className="info-box-left-text">Absolwent kierunku Elektrotechnika, wydziału elektrycznego Politechniki Wrocławskiej.
                            Moje zainteresowanie programowaniem rozpoczęło się w trakcie studiów, podczas odbywania kursu
                            podstaw programowania w C. Zwykła ciekawość poprowadziła mnie przez obszar kolejnego języka programowania,
                            C++. Chęć zgłębienia każdego aspektu tego języka przerodziła się w pasję,
                            która zaowocowała stworzeniem kilku swoich własnych projektów. Jednakże w dalszym ciągu niezaspokojone
                            zaciekawienie programowaniem, poskutkowało nieodwrotnym skokiem w strefę dalszych języków
                            wykorzystywanych w innych dziedzinach technologicznych. W związku z tym następnym etapem
                            było stworzenie kilku aplikacji desktopowych w języku programowania Java. Równocześnie poszerzałem
                            swoją wiedzę na temat elektroniki i w następstwie tego zrealizowałem parę projektów opartych o
                            mikrokontrolery AVR w języku C oraz assemblerze. Ostatecznie w swojej wędrówce dotarłem do technologii
                            wykorzystywanych w przeglądarkach internetowych, tworząc parę aplikacji internetowych opartych na
                            frameworku JavaScript, React. Ze względu, iż miałem uprzednio styczność z Javą, aktualnie rozwijam
                            swoją wiedzę na temat programowania serwisów obsługujących strony internetowe, znajdujących się
                            po stronie serwera. Swoje aplikacje staram się pisać sumiennie i rzetelnie,
                            dbając jednocześnie o czystość kodu. Oprócz pasji programowania czynnie uprawiam sporty
                            ekstremalne takie jak: deskorolka, windsurfing i snowboard. Dodatkowo interesuję się
                            komponowaniem muzyki elektronicznej w popularnych programach muzycznych typu DAW oraz uczę się
                            gry na pianinie.</h3>
                    </div>
                    <div className="img-box-right" id="img-box-right">
                        <img src="../../img/star-wars.png" id="star-wars"/>
                        <img src="../../img/star-wars-head.png" id="star-wars-head"/>
                        <div id="star-wars-plane"></div>
                    </div>
                    <div id="bullet"></div>
                    <div className="info-box-right">
                        <h1 className="info-box-right-head">Doświadczenie</h1>
                        <div className="lang-row" id="cplus" >
                            <h3 className="lang-head">Średnio zaawansowany</h3>
                            <LangBar width={120} lang={"cplus"}/>
                            <h3 className="lang-info">C ++</h3>
                        </div>
                        <div className="lang-row" id="java">
                            <h3 className="lang-head">Średni</h3>
                            <LangBar width={90} lang={"java"}/>
                            <h3 className="lang-info">Java</h3>
                        </div>
                        <div className="lang-row" id="spring">
                            <h3 className="lang-head">Początkujący</h3>
                            <LangBar width={30} lang={"spring"}/>
                            <h3 className="lang-info">Spring</h3>
                        </div>
                        <div className="lang-row" id="javascript">
                            <h3 className="lang-head">Średni</h3>
                            <LangBar width={100} lang={"javascript"}/>
                            <h3 className="lang-info">JavaScript</h3>
                        </div>
                        <div className="lang-row" id="css">
                            <h3 className="lang-head">Średni</h3>
                            <LangBar width={81} lang={"css"}/>
                            <h3 className="lang-info">CSS</h3>
                        </div>
                    </div>
                    <HuntingGame/>
                    <div className="info-box-left">
                            <h1 className="info-box-left-head info-box-left-head2">Doświadczenie</h1>
                            <div className="lang-row" id="assembler" >
                                <h3 className="lang-head">Początkujący</h3>
                                <LangBar width={20} lang={"assembler"}/>
                                <h3 className="lang-info">Assembler</h3>
                            </div>
                            <div className="lang-row" id="avr">
                                <h3 className="lang-head">Średni</h3>
                                <LangBar width={67} lang={"avr"}/>
                                <h3 className="lang-info">AVR w C</h3>
                            </div>
                            <div className="lang-row" id="react">
                                <h3 className="lang-head">Średni</h3>
                                <LangBar width={110} lang={"react"}/>
                                <h3 className="lang-info">React</h3>
                            </div>
                            <div className="lang-row" id="qt">
                                <h3 className="lang-head">Średni</h3>
                                <LangBar width={78} lang={"qt"}/>
                                <h3 className="lang-info">Qt</h3>
                            </div>
                            <div className="lang-row" id="openCV">
                                <h3 className="lang-head">Średni</h3>
                                <LangBar width={61} lang={"openCV"}/>
                                <h3 className="lang-info">OpenCV</h3>
                            </div>
                    </div>

                    <div className="img-box-right img-box-right-border">
                        <h1 className="info-box-right-head">Kursy</h1>
                        <img src="../../img/diploma-logo.png" id="diploma" onClick={this.onFullscreenImage.bind(this)}/>
                    </div>

                    <div className="info-box-left" style={{height: 360}}>
                        <h1 className="info-box-left-head">Kontakt</h1>
                        <div className="lang-row">
                            <h3 className="lang-head" style={{width:100, paddingTop: 4}}>E-mail:</h3>
                            <h3 className="lang-info">michal.olech.mail@gmail.com</h3>
                        </div>
                        <div className="lang-row">
                            <h3 className="lang-head">Numer telefonu:</h3>
                            <h3 className="lang-info">513514703</h3>
                        </div>
                    </div>

                    <div className="img-box-right img-box-right-border" style={{height: 360}}>
                        <h1 className="info-box-right-head">Linki</h1>
                        <a className="menu-btn btn-links btn-align" style={{background: "#B0B0B0"}}
                             target="_blank" rel="noopener noreferrer"
                             href="https://github.com/micavanco">GitHub</a>
                        <a className="menu-btn btn-links btn-align" style={{background: "#DA9A2A"}} href="">LinkedIn</a>
                    </div>

                    <div id="fullscreen-image-content"><img id="fullscreen-image" src={"../../img/diploma.png"}/>
                        <div id="exit-cross-container" onClick={this.onFullscreenExit.bind(this)}>
                            <div id="exit-cross"></div>
                        </div>
                    </div>
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

    componentDidMount() {
        if(!wasLoaded)
        {
            wasLoaded = true;
            let isOn = false;
            document.getElementById("info-box-left").addEventListener("mousemove", (e)=>{
                if(document.body.clientWidth > 1415)
                {
                    let rect = document.getElementById("info-box-left").getBoundingClientRect();
                    let x = 1200-(e.screenX-rect.left);
                    let y = 342-(e.screenY-rect.top);
                    let deg = Math.atan(y/x)*57.29577;
                    //console.log("x = "+x.toString()+" y= "+y.toString()+" deg = "+deg.toString());
                    document.getElementById("star-wars-head").style.transform = "rotateZ("+deg.toString()+"deg)";
                    x = 1060-(e.screenX-rect.left);
                    y = 342-(e.screenY-rect.top);
                    deg = Math.atan(y/x)*57.29577;
                    let bullet = document.getElementById("bullet");
                    bullet.style.opacity = "1";
                    bullet.style.transform = "rotateZ("+deg.toString()+"deg)";
                    bullet.style.marginLeft = (1172-x).toString()+"px";
                    bullet.style.marginTop = (355-y).toString()+"px";
                    if(!isOn)
                    {
                        isOn = true;
                        setTimeout(() =>{
                            bullet.style.display = "none";
                            bullet.style.marginLeft = "1172px";
                            bullet.style.marginTop = "355px";
                        }, 1000);
                        setTimeout(()=>{
                            bullet.style.display = "block";
                            bullet.style.opacity = "0";
                            isOn = false;
                        },1100);
                    }
                }
            });

            document.getElementById("star-wars-plane").addEventListener("click", (e)=>{
                if(document.body.clientWidth > 1415)
                {
                    let bullet = document.getElementById("bullet");
                    let rect = e.target.getBoundingClientRect();
                    let x = 405-(e.screenX-rect.left);
                    let y = 342-(e.screenY-rect.top);
                    let deg = Math.atan(y/x)*57.29577;
                    bullet.style.opacity = "1";
                    bullet.style.transform = "rotateZ("+deg.toString()+"deg)";
                    bullet.style.marginLeft = (1172-x).toString()+"px";
                    bullet.style.marginTop = (355-y).toString()+"px";
                    document.getElementById("star-wars-head").style.transform = "rotateZ("+deg.toString()+"deg)";
                    setTimeout(() =>{
                        bullet.style.display = "none";
                        bullet.style.marginLeft = "1172px";
                        bullet.style.marginTop = "355px";
                    }, 700);
                    setTimeout(()=>{
                        bullet.style.display = "block";
                        bullet.style.opacity = "0";
                    },800);
                }
                else if(document.body.clientWidth < 1332)
                {
                    let bullet = document.getElementById("bullet");
                    let rect = e.target.getBoundingClientRect();
                    let x = 405-(e.screenX-rect.left);
                    let y = 342-(e.screenY-rect.top);
                    let deg = Math.atan(y/x)*57.29577;
                    bullet.style.opacity = "1";
                    bullet.style.transform = "rotateZ("+deg.toString()+"deg)";
                    bullet.style.marginLeft = (500-x).toString()+"px";
                    bullet.style.marginTop = (1006-y).toString()+"px";
                    document.getElementById("star-wars-head").style.transform = "rotateZ("+deg.toString()+"deg)";
                    setTimeout(() =>{
                        bullet.style.display = "none";
                        bullet.style.marginLeft = "500px";
                        bullet.style.marginTop = "1006px";
                    }, 700);
                    setTimeout(()=>{
                        bullet.style.display = "block";
                        bullet.style.opacity = "0";
                    },800);
                }
            });
        }
    }

    onFullscreenExit()
    {
        document.getElementById("fullscreen-image-content").style.display = "none";
    }

    onFullscreenImage()
    {
        document.getElementById("fullscreen-image-content").style.display = "flex";
    }

}

export default MainPage;
