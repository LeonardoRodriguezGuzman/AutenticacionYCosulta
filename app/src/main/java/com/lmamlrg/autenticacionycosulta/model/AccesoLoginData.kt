import org.simpleframework.xml.Element
import org.simpleframework.xml.Namespace
import org.simpleframework.xml.Root

@Root(name = "Envelope")
@Namespace(prefix = "soap", reference = "http://schemas.xmlsoap.org/soap/envelope/")
data class SoapEnvelope(
    @field:Element(name = "Body")
    var body: SoapBody
)

@Root(name = "Body")
data class SoapBody(
    @field:Element(name = "accesoLogin", required = false)
    var accesoLogin: AccesoLogin
)

@Root(name = "accesoLogin")
@Namespace(reference = "http://tempuri.org/")
data class AccesoLogin(
    @field:Element(name = "strMatricula")
    var strMatricula: String,

    @field:Element(name = "strContrasenia")
    var strContrasenia: String,

    @field:Element(name = "tipoUsuario")
    var tipoUsuario: String
)
