package com.lmamlrg.autenticacionycosulta.model

import org.simpleframework.xml.Element
import org.simpleframework.xml.Root
import org.simpleframework.xml.Namespace
import org.simpleframework.xml.NamespaceList

//AccesoAlumno
@Root(name = "Envelope")
@Namespace(prefix = "soap", reference = "http://schemas.xmlsoap.org/soap/envelope/")
class AccesoAlumnoEnvelope {
    @field:Element(name = "Body")
    var body: AccesoAlumnoBody? = null
}

class AccesoAlumnoBody {
    @field:Element(name = "accesoLoginResponse", required = false)
    @field:Namespace(reference = "http://tempuri.org/")
    var response: AccesoAlumnoResponse? = null
}

class AccesoAlumnoResponse {
    @field:Element(name = "accesoLoginResult", required = false)
    var result: String? = null
}


//DatosAlumno
@Root(name = "Envelope")
@Namespace(prefix = "soap", reference = "http://schemas.xmlsoap.org/soap/envelope/")
class DatosAlumnoEnvelope {
    @field:Element(name = "Body")
    var body: DatosAlumnoBody? = null
}

class DatosAlumnoBody {
    @field:Element(name = "getAlumnoAcademicoWithLineamientoResponse", required = false)
    @field:Namespace(reference = "http://tempuri.org/")
    var response: DatosAlumnoResponse? = null
}

class DatosAlumnoResponse {
    @field:Element(name = "getAlumnoAcademicoWithLineamientoResult", required = false)
    var result: String? = null
}
