export class EDITAlumnoDTO{
    nombre: string
    apellidos: string
    email: string
    telefono: string
    constructor(nombre: string, apellidos: string, email:string, telefono: string){
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.email = email;
        this.telefono = telefono;
    }
}