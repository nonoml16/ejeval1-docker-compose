// Generated by https://quicktype.io

export interface AlumnoProfesorListResponse {
    content:       AlumnoP[];
    size:          number;
    totalElements: number;
    pageNumber:    number;
    first:         boolean;
    last:          boolean;
}

export interface AlumnoP {
    id:              string;
    nombreApellidos: string;
    fechaNacimiento: string;
}
