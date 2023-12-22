export class POSTInstrumentoDTO{
    nombre: String
    contenidos: String
    fecha: string
    referentes: String[]
    constructor(name:string, content:string, date:string, refs: String[]){
        this.nombre = name;
        this.contenidos = content;
        this.fecha = date;
        this.referentes = refs;
    }
}