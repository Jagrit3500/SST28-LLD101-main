public abstract class Exporter {

    public final ExportResult export(ExportRequest req) {

        if (req == null) {
            throw new IllegalArgumentException("request cannot be null");
        }

        if (!supports(req)) {
            throw new IllegalArgumentException(errorMessage());
        }

        return doExport(req);
    }

    protected boolean supports(ExportRequest req) {
        return true;
    }

    protected String errorMessage() {
        return "unsupported request";
    }

    protected abstract ExportResult doExport(ExportRequest req);
}