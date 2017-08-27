#include "mainwindow.h"
#include "ui_mainwindow.h"

#include <QDebug>

double firstNumber;
double secondNumber;
double labelNumber;
int isTyping=1;
bool isChecked=false;
QPushButton *last;

MainWindow::MainWindow(QWidget *parent) :
    QMainWindow(parent),
    ui(new Ui::MainWindow)
{
    ui->setupUi(this);

    connect(ui->pushButton_zero,SIGNAL(released()),this,SLOT(digit_pressed()));
    connect(ui->pushButton_1,SIGNAL(released()),this,SLOT(digit_pressed()));
    connect(ui->pushButton_2,SIGNAL(released()),this,SLOT(digit_pressed()));
    connect(ui->pushButton_3,SIGNAL(released()),this,SLOT(digit_pressed()));
    connect(ui->pushButton_4,SIGNAL(released()),this,SLOT(digit_pressed()));
    connect(ui->pushButton_5,SIGNAL(released()),this,SLOT(digit_pressed()));
    connect(ui->pushButton_6,SIGNAL(released()),this,SLOT(digit_pressed()));
    connect(ui->pushButton_7,SIGNAL(released()),this,SLOT(digit_pressed()));
    connect(ui->pushButton_8,SIGNAL(released()),this,SLOT(digit_pressed()));
    connect(ui->pushButton_9,SIGNAL(released()),this,SLOT(digit_pressed()));

    connect(ui->pushButton_plus_minus,SIGNAL(released()),this,SLOT(unary_operation_pressed()));
    connect(ui->pushButton_percent,SIGNAL(released()),this,SLOT(unary_operation_pressed()));

    connect(ui->pushButton_plus,SIGNAL(released()),this,SLOT(binary_operations_pressed()));
    connect(ui->pushButton_minus,SIGNAL(released()),this,SLOT(binary_operations_pressed()));
    connect(ui->pushButton_multiply,SIGNAL(released()),this,SLOT(binary_operations_pressed()));
    connect(ui->pushButton_div,SIGNAL(released()),this,SLOT(binary_operations_pressed()));

    ui->pushButton_plus->setCheckable(true);
    ui->pushButton_minus->setCheckable(true);
    ui->pushButton_div->setCheckable(true);
    ui->pushButton_multiply->setCheckable(true);
}

MainWindow::~MainWindow()
{
    delete ui;
}

void MainWindow::digit_pressed()
{
    QPushButton *button=(QPushButton*)sender();

    QString string;
    if((ui->pushButton_plus->isChecked()||ui->pushButton_minus->isChecked()||
            ui->pushButton_div->isChecked()||ui->pushButton_multiply->isChecked())&&(!isChecked))
    {
        ui->label->setText(button->text());
        labelNumber=button->text().toDouble();
        isChecked=true;
    }else
    {
        if((ui->label->text().contains('.'))&&(button->text()=="0"))
        {
            string=ui->label->text()+button->text();
        }
        else
        {
            labelNumber=(ui->label->text()+button->text()).toDouble();
            string=QString::number(labelNumber,'g',15);
        }
        ui->label->setText(string);
    }

}

void MainWindow::on_pushButton_dot_released()
{
    if(ui->label->text().contains('.'));
    else
    ui->label->setText(ui->label->text()+'.');
}

void MainWindow::unary_operation_pressed()
{
    QPushButton *button=(QPushButton*)sender();

    QString string;

    if(button->text()=="+/-")
    {
        labelNumber=ui->label->text().toDouble();
        labelNumber=labelNumber*-1;
        string=QString::number(labelNumber,'g',15);
        ui->label->setText(string);
    }
    else if(button->text()=="%")
    {
        labelNumber=ui->label->text().toDouble();
        labelNumber=labelNumber*0.01;
        string=QString::number(labelNumber,'g',15);
        ui->label->setText(string);
    }
    else if(button->text()=="-")
        ui->label->setText(ui->label->text()+button->text());
}

void MainWindow::on_pushButton_del_released()
{
    ui->label->setText("0");
    isTyping=1;
    isChecked=false;
    ui->pushButton_equals->setChecked(false);
    ui->pushButton_plus->setChecked(false);
    ui->pushButton_minus->setChecked(false);
    ui->pushButton_div->setChecked(false);
    ui->pushButton_multiply->setChecked(false);
}

void MainWindow::on_pushButton_equals_released()
{
    if(isTyping==2)
    {
        QString string;

        secondNumber=ui->label->text().toDouble();

        if(last==ui->pushButton_plus)
        {
            labelNumber=firstNumber+secondNumber;
            string=QString::number(labelNumber,'g',15);
            ui->label->setText(string);
        }
        else if(last==ui->pushButton_minus)
        {
            labelNumber=firstNumber-secondNumber;
            string=QString::number(labelNumber,'g',15);
            ui->pushButton_minus->setChecked(false);
            ui->label->setText(string);
        }
        else if(last==ui->pushButton_multiply)
        {
            labelNumber=firstNumber*secondNumber;
            string=QString::number(labelNumber,'g',15);
            ui->pushButton_multiply->setChecked(false);
            ui->label->setText(string);
        }
        else if(last==ui->pushButton_div)
        {
            if(secondNumber!=0)
            {
                labelNumber=firstNumber/secondNumber;
                string=QString::number(labelNumber,'g',15);
            }else
                string="Dzielenie przez zero!";
            ui->pushButton_div->setChecked(false);
            ui->label->setText(string);
        }
    }
    isTyping=1;
}

void MainWindow::binary_operations_pressed()
{
    QPushButton *button=(QPushButton*)sender();
    button->setChecked(true);
    if(isTyping>1)
    {
        QString string;

        secondNumber=ui->label->text().toDouble();

        if(last==ui->pushButton_plus)
        {
            labelNumber=firstNumber+secondNumber;
            string=QString::number(labelNumber,'g',15);
            ui->label->setText(string);
        }
        else if(last==ui->pushButton_minus)
        {
            labelNumber=firstNumber-secondNumber;
            string=QString::number(labelNumber,'g',15);
            ui->pushButton_minus->setChecked(false);
            ui->label->setText(string);
        }
        else if(last==ui->pushButton_multiply)
        {
            labelNumber=firstNumber*secondNumber;
            string=QString::number(labelNumber,'g',15);
            ui->pushButton_multiply->setChecked(false);
            ui->label->setText(string);
        }
        else if(last==ui->pushButton_div)
        {
            if(secondNumber!=0)
            {
                labelNumber=firstNumber/secondNumber;
                string=QString::number(labelNumber,'g',15);
            }else
                string="Dzielenie przez zero!";
            ui->pushButton_div->setChecked(false);
            ui->label->setText(string);
        }
    }
    last=button;
    firstNumber=ui->label->text().toDouble();
    isChecked=false;
    isTyping++;
}
