public class ReporteDesempenio {
    private Empleado empleado;
    private String evaluacion;
    private String comentarios;

    public ReporteDesempenio(Empleado empleado, String evaluacion, String comentarios) {
        this.empleado = empleado;
        this.evaluacion = evaluacion;
        this.comentarios = comentarios;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public String getEvaluacion() {
        return evaluacion;
    }

    public String getComentarios() {
        return comentarios;
    }

    @Override
    public String toString() {
        return String.format(
                "------------------------------------\n" +
                        "Reporte de Desempeño del Empleado:\n" +
                        "Nombre: %s %s\n" +
                        "ID: %s\n" +
                        "Evaluación: %s\n" +
                        "Comentarios: %s\n" +
                        "------------------------------------\n",
                empleado.getNombre(),  // Suponiendo que Empleado tiene un método getNombre()
                empleado.getApellido(),  // Suponiendo que Empleado tiene un método getApellido()
                empleado.getId(),  // Suponiendo que Empleado tiene un método getId()
                evaluacion,
                comentarios
        );
    }
}

