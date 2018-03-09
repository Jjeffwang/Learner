/**
 * Created by 01054474 on 2018/3/9.
 */
//第一种方式
// var Person = {
//     name: "tom",
//     age: "25",
//     eat: function () {
//         alert("hunger!");
//     }
// }
// alert(Person.name);


//第二种方式
// function Person() {
//
// }
// Person.prototype = {
//     name: "tom",
//     age: "25",
//     eat: function () {
//         alert("hunger!");
//     }
// }
//
// //继承
// function Student() {
//
// }
// Student.prototype=new Person();
// Student.prototype.eat=function () {
//     alert("no hunger!")
// }
// var s=new Student();
// alert(s.eat());


//第三种
function person(firstname,lastname,age,eyecolor)
{
    this.firstname=firstname;
    this.lastname=lastname;
    this.age=age;
    this.eyecolor=eyecolor;
}