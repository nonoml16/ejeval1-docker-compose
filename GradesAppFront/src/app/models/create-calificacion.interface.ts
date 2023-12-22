export class POSTCalificacionDTO{
    codRef: string;
    idAlumno: string;
    calificacion: number;
    constructor(codRefer: string, alumnoId:string, calf: number){
        this.codRef = codRefer;
        this.idAlumno = alumnoId;
        this.calificacion = calf;
    }
}