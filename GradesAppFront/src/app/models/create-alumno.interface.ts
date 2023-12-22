export class POSTAlumnoDTO{
    nombre: string
    apellidos: string
    fechaNacimiento: string
    email: string
    telefono: string
    username: string
    password: string
    constructor(nombre:string, apellidos:string, fechaNacimiento: string, email:string, telefono: string, username: string, password: string){
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.fechaNacimiento = fechaNacimiento;
        this.email = email;
        this.telefono = telefono;
        this.username = username;
        this.password = password;
    }
}