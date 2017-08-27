/*---------------------------------------------------------------
    Michal Olech
    219588
    Semestr: IV
    Rok:     2
*///-------------------------------------------------------------
#include <stdio.h>
#include <stdlib.h>
#include <time.h>

void kolory()
{
    printf("---------------- Dostepne kolory ----------------\n");
    printf("        0 = Czarny\n");
    printf("        1 = Niebieski\n");
    printf("        2 = Zielony\n");
    printf("        3 = Zielono-Niebieski\n");
    printf("        4 = Czerwony\n");
    printf("        5 = Fioletowy\n");
    printf("        6 = Zolty\n");
    printf("        7 = Bialy\n");                                    //funkcja wyswietlajaca opcja menu
    printf("        9 = Jasno-Niebieski\n");
    printf("        A = Jasno-Zielony\n");
    printf("        B = Jasno-Zielono-Niebieski\n");
    printf("        C = Jasno-Czerwony\n");
    printf("        D = Jasno-Fioletowy\n");
    printf("        E = Jasno-Zolty\n");
    printf("        F = Jasno-Bialy\n");
    printf("        Wpisz wartosc (np. COLOR 12) :");
    return;
}

struct wezel
{
    char atr; //atrybuty dostêpu do wezla: 'Z', 'B', 'W'
    double pot; //potencjal w wezle siatki                 //struktura posiadajcace dane atrybuty dostepu
};

double wpis()
{
    double x;
    printf("Wpisz liczbe: \n");
    scanf("%f", &x);                   //funkcja odbierajaca od uzytkownika zmienna i zwracajaca ja
    return x;
}

void delay(unsigned int mseconds)
{
    clock_t goal = mseconds + clock();  //funkcja odliczajaca w milisekundach z biblioteki <time.h>
    while (goal > clock());
}

double sinus(int c,double d)
{
    double x=0.0;
    int i=1,j=1,s,k;                  //funkcja obliczajaca na podstawie ilosci iteracji i zmiennej, funkcje sinus
    for(k=0;k<c;k++)
    {
        s=(2*k)+1;
        for(i=1;i<=s;i++)
        {
            j=j*i;           //obliczanie silni dla(2*i)+1
        }
        x=x+((pow(-1.0,k)*pow(d,s))/j); //funkcja szeregu sin(1.0)
        j=1;
    }
    return x;
}
//zmienne globalne
struct wezel obszar[11][11];
//----------------------------------
void wlaczanie_ostrza(int ostrze_x,int sz_ostrza,int dl_ostrza)
{
    int i,j;
    for(i=1;i<dl_ostrza;i++)
    {
        for(j=ostrze_x;j<sz_ostrza+ostrze_x;j++)       //funkcja zmienajaca atrybut danego element macierzy wedlug zadanych
        {                                              //ograniczen, przyjetych jako zmienne integer
            obszar[i][j].atr='B';
        }
    }
    return;
}

void wylaczanie_ostrza()
{
    int i,j;
    for(i=1;i<10;i++)
    {
        for(j=1;j<10;j++)              //funkcja zmieniajaca atrybuty wszystkich elementow macierzy na 'W'
        {                              //czyli wezel wewnetrzny "odczyt/zapis"
            obszar[i][j].atr='W';
        }
    }
    return;
}

int main()
{
    int ostrze_wl=0,los=0,ostrze_help=0,licz=0,licznik=0;
    int liczby_losowe_x[5]={0,0,0,0,0};
    int liczby_losowe_y[5]={0,0,0,0,0};
    int i,j,k=0,k2=0,war=0,k3=0,h=0,s,o,ostrze_l=5,ostrze_w=1,ostrze_x=5;
    char kolor_cz='0',kolor_tla='7',a[9];
    double user=10.0,s1;                          //deklaracja zmiennych
    double tablica_sin[9];
    double PI=0.3490658503988659;
    for(i=0;i<11;i++)
    {
        for(j=0;j<11;j++)
        {
            if((j>0&&j<10)&&i==0)obszar[i][j].atr='B';
            else
            {
                if(j==0||j==10||i==10)
                {
                   obszar[i][j].atr='Z';         //zmiana atrybotow wedlug zalozen zadania
                   obszar[i][j].pot=0.0;
                }
                else
                {
                    obszar[i][j].atr='W';
                    obszar[i][j].pot=0.0;
                }
            }
        }
    }

    while(1)
    {
        for(i=0;i<11;i++)
        {
            for(j=0;j<11;j++)
            {
                if(obszar[i][j].atr=='W')
                {
                    obszar[i][j].pot=0.25*(obszar[i][j-1].pot+obszar[i][j+1].pot+obszar[i+1][j].pot+obszar[i-1][j].pot); //iteracja obszaru wewnetrznego
                }else if(obszar[i][j].atr=='B')
                {
                    if(k3==0)obszar[i][j].pot=user;
                    else if(k3==1)
                    {
                        obszar[i][j].pot=tablica_sin[j-1]; //zmiana wartosci potencjalow wezlow brzegowych na wartosci obliczone z x*sin(j)
                    }
                }
                if(j==0){printf("%.2f",obszar[i][j].pot);}
                else if(j==10){printf("  %.2f", obszar[i][j].pot);}
                else if((obszar[i][j].pot<10.0)&&(j!=0 && j!=10)){printf("   %.2f", obszar[i][j].pot);}
                else if((obszar[i][j].pot>=10.0&&(j!=0 && j!=10))&&(obszar[i][j].pot<100.0)){printf("  %.2f", obszar[i][j].pot);} //wypis obliczonych wartosci na ekran
                else if((obszar[i][j].pot>=100.0&&(j!=0 && j!=10))&&(obszar[i][j].pot<1000.0)){printf(" %.2f", obszar[i][j].pot);}
            }
            printf("\n");
        }
        licz++;
        if(licznik)printf("\n                  Iteracja Nr: %i\n", licz);
        printf("\n                  Warunek brzegowy dla gornej krawedzi: ");
        if(war==0)printf("const = %.2lf V\n", user);
        else printf("x*Sin(j); dla 0<=j<=PI\n");
        printf("----------------- Nacisnij dowolny klawisz aby wejsc do Menu --------------\n\n");
        delay(160);
        if(kbhit()) //sprawdzenie czy zostal wcisniety klawisz
        {
            printf("-------------------------- Menu -----------------------------\n");
            printf("     Wybierz opcje wciskajac odpowiedni klawisz (np. 1)\n");
            printf("-------------------------------------------------------------\n");
            printf("                  1 - Zmiana potencjalu\n");
            printf("                  2 - Konfiguracja ostrza\n");
            printf("                  3 - Wl/Wyl licznika iteracji\n");                 //glowne menu programu
            printf("                  4 - Wyczysc wszystkie warosci\n");
            printf("                  5 - Zmiana koloru\n");
            printf("                  6 - Wyjscie                         Created by:Michal Olech\n");
            printf("                  Numer: ");
            scanf("%i", &k);
            switch (k)                      //wybor jednej z opcji przez uzytkownika
            {
                case 1:
                {
                    printf("-------------------------------------------------------------\n");
                    printf("                  1 - Nowa wartosc potencjalu \n");
                    printf("                  2 - Sinus dla 0<=j<=PI\n");
                    printf("                  Numer: ");
                    scanf("%i", &k2);
                    if(k2==1)
                    {
                        printf("        Aby ustawic nowa wartosc potencjalu wpisz liczbe (np. 12.2) :");
                        scanf("%lf", &user);
                        k3=0;                                                         //zmiana wartosci potencjalu wezlow brzegowych
                        war=0;
                    }else if(k2==2)
                    {
                        war=1;
                        printf("                  Ilosc iteracji sinus(max 10): ");         //zmiana wartosci potencjalu wezlow brzegowych oblicznonych
                        scanf("%i", &s);                                                      //na podstawie funkcji sinus
                        printf("        Wartosc potencjalu przekazywana do funkcji sinus(np. 12.2): ");
                        scanf("%lf", &user);
                        printf("                  Przeliczanie wartosci. Prosze czekac.");
                        for(h=1;h<10;h++)
                        {
                            s1=PI*h;
                            if(h==5)tablica_sin[h-1]=user;
                            else if(h<5)tablica_sin[h-1]=user*sinus(s,s1);      //wpisz wartosci oblicznych z funkcji sinus do specjalanej tabeli
                            else tablica_sin[h-1]=tablica_sin[9-h];
                        }
                        k3=1;
                    }else printf("     Niepoprawna wartosc!\n"); //jezeli uzytkownik wybral opcje z poza zakresu menu
                    break;
                }
                case 2:
                {
                    printf("-------------------------------------------------------------\n");
                    printf("                  1 - Wl/Wyl ostrze \n");
                    printf("                  2 - Przesun ostrze\n");                      //wypis opcji z zakladki nr 2 z menu glownego
                    printf("                  3 - Zmien wymiary\n");
                    printf("                  4 - Losowa pozycja\n");
                    printf("                  Numer: ");
                    scanf("%i", &o);
                    switch (o)
                    {
                        case 1:
                        {
                            if(ostrze_wl==0)
                            {
                                wlaczanie_ostrza(ostrze_x,ostrze_w,ostrze_l);
                                ostrze_wl=1;
                            }else                                                //przypadek pierwszy wlacza i wylacza ostrze
                            {
                                wylaczanie_ostrza();
                                ostrze_wl=0;
                                los=0;
                            }
                            break;
                        }
                        case 2:
                        {
                            if(los==1)
                            {
                               printf("                  Funkcja losowa wlaczona \n"); delay(1000); //sprawdzenie czy zostala wczesniej wybrana opcja funkcji losowej i nie zostala wylaczona
                               break;
                            }
                            if(ostrze_wl)
                            {
                                printf("        Wpisz pozycje ostrza (1<=x<=9) :");
                                scanf("%i", &ostrze_help);                       //wpis nowej wartosci polozenia ostrza w osi poziomej
                                if(ostrze_help-1+ostrze_w<10)
                                {
                                    wylaczanie_ostrza();
                                    ostrze_x=ostrze_help;
                                    wlaczanie_ostrza(ostrze_x,ostrze_w,ostrze_l);
                                }else
                                {
                                    printf("Nie mozna wykonac polecenia..."); //jezeli ostrze ma zbyt duzy rozmiar lub zostala wybrana niepoprawana wartosc i ostrze nie meisci sie w zakresie
                                    delay(1500);
                                }
                            }else
                            {
                                printf("                  Ostrze wylaczone \n"); //jezeli uzytkownik niewlaczyl wpierw ostrza
                                delay(1000);
                            }
                            break;
                        }
                        case 3:
                        {
                            if(los==1)
                            {
                               printf("                  Funkcja losowa wlaczona \n"); //sprawdzenie czy zostala wczesniej wybrana opcja funkcji losowej i nie zostala wylaczona
                               delay(1000);
                               break;
                            }
                            if(ostrze_wl)
                            {
                                do
                                {
                                    printf("        Wpisz szerokosc ostrza (1<=x<=4) :");
                                    scanf("%i", &ostrze_help);                                 //wpis nowej wartosci szerokosci ostrza
                                    if(ostrze_help-1+ostrze_x>=10)  //sprawdzenie czy wartosci nie przekraczaja zakresu
                                    {
                                        printf("        Nie mozna wykonac polecenia.\n        Prosze sprobowac jeszcze raz.\n");
                                        delay(1500);
                                    }
                                }while(ostrze_help-1+ostrze_x>=10);
                                wylaczanie_ostrza();
                                printf("        Wpisz dlugosc ostrza (1<=y<=9) :");
                                scanf("%i", &ostrze_l);
                                ostrze_w=ostrze_help;
                                wlaczanie_ostrza(ostrze_x,ostrze_w,ostrze_l);   //przekazanie do funkcji wartosci rozmiaru i polozenia oraz wywolanie jej
                            }else
                              {
                                printf("                  Ostrze wylaczone \n"); //jezeli uzytkownik niewlaczyl wpierw ostrza
                                delay(1000);
                              }
                            break;
                        }
                        case 4:
                        {
                            if(ostrze_wl)
                            {
                                    los=1;
                                    wylaczanie_ostrza();
                                    for(j=0;j<6;j++)
                                    {
                                        liczby_losowe_x[j]=rand()%10; //funkcja zwracajaca losowe liczby z zakresy 0<x<10 w celu wylosowania miejsc wezlow brzegowych w obszarze wezlow wewnetrznych
                                        liczby_losowe_y[j]=rand()%10;
                                        while(liczby_losowe_x[j]==0)liczby_losowe_x[j]=rand()%10;
                                        while(liczby_losowe_y[j]==0)liczby_losowe_y[j]=rand()%10;
                                    }
                                    for(j=0;j<6;j++)
                                    {
                                        obszar[liczby_losowe_x[j]][liczby_losowe_y[j]].atr='W';  //zmiana atrybutow z ostatniego wywolania w celu przygotowania macierzy na nowe wywolanie
                                    }
                                    for(j=0;j<6;j++)
                                    {
                                        obszar[liczby_losowe_x[j]][liczby_losowe_y[j]].atr='B';
                                        obszar[liczby_losowe_x[j]][liczby_losowe_y[j]].pot=rand()%100; //funkcja zwracajaca losowe liczby z zakresy 0<x<100 w celu ostawienia losowego potencjalu
                                    }
                              }else
                              {
                                printf("                  Ostrze wylaczone \n"); delay(1000); //jezeli uzytkownik niewlaczyl wpierw ostrza
                              }
                            break;
                        }
                        default:
                        {
                            printf("     Niepoprawna wartosc!\n"); //jezeli uzytkownik wybral wartosc spoza zakresu menu
                            delay(1000);
                        }
                    }
                    break;
                }
                case 3:
                {
                    if(licznik==0)licznik=1; //wlaczenie i wylaczenie licznika iteracji
                    else licznik=0;
                    break;
                }
                case 4:
                {
                    for(i=1;i<10;i++)
                        {
                            for(j=1;j<10;j++)
                            {
                                    obszar[i][j].atr='W'; //ustawienie calego obszaru wezlow wewnetrznych na atrybut 'W'
                                    obszar[i][j].pot=0.0; //wyzerowanie potencjalu calego obszaru wezlow wewnetrznych
                            }
                        }
                        licz=0; //zerowanie licznika iteracji
                        break;
                }
                case 5:
                {
                    printf("-------------------------------------------------------------\n");
                    printf("                  1 liczba - Zmieana tla\n");
                    printf("                  2 liczba - Zmiana koloru czcionki\n");
                    printf("              Nalezy wpisac:COLOR spacja i dwie liczby ->COLOR 12\n"); //menu odpowiedzialne zamiane kolorow
                    printf("-------------------------------------------------------------\n");
                    kolory();
                    fflush(stdin);             //czyszczenie buffora wejsciowego w celu pozbycia sie wszelkich wcisnietych wartosci z klawiatury
                    const char *b=fgets(a,9,stdin); //wpisz lancucha znakow do wejscia
                    system(b); //zmiana kolorow
                    break;
                }
                case 6:
                {
                    exit(0); //zakonczenie programu
                }
                default:
                    {
                        printf("     Niepoprawna wartosc!\n");  //jezeli uzytkownik wybral wartosc spoza zakresu menu
                        delay(1000);
                    }
            }
        }
        fflush(stdin); //czyszczenie buffora wejsciowego w celu pozbycia sie wszelkich wcisnietych wartosci z klawiatury
        system("cls"); //funkcja odpowiedzialna za czyszczenie ekranu
    }
    return 0;
}
