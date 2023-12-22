export class POSTReferenteDTO{
    codReferente: String
    descripcion: String
    constructor(codRef:string, desc:string){
        this.codReferente = codRef;
        this.descripcion = desc;
    }
}