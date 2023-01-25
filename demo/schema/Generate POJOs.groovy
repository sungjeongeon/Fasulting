import com.intellij.database.model.DasTable
import com.intellij.database.model.ObjectKind
import com.intellij.database.util.Case
import com.intellij.database.util.DasUtil

typeMapping = [
        (~/(?i)bigint/)  : "Long",
        (~/(?i)int|tinyint|smallint|mediumint/)  : "Integer",
        (~/(?i)bool|bit/)                        : "Boolean",
        (~/(?i)float|double|decimal|real|number/): "BigDecimal",
        (~/(?i)datetime|timestamp|date|time/)    : "LocalDateTime",
        (~/(?i)binary|bfile|raw|image/)          : "InputStream",
        (~/(?i)blob|clob/)                       : "lob",
        (~/(?i)/)                                : "String"
]


FILES.chooseDirectoryAndSave("Choose directory", "Choose where to store generated files") { dir ->
    SELECTION.filter { it instanceof DasTable && it.getKind() == ObjectKind.TABLE }.each { generate(it, dir) }
}


def generate(table, dir) {
    def className = javaName(table.getName(), true).toString() + "Entity"
    def fields = calcFields(table)
    packageName = getPackageName(dir)
    // solve the garbled problem
    new File(dir, className + ".java").withPrintWriter("utf-8") { out -> generate(out, className, fields, table) }
}


// Get the folder path of the package
def getPackageName(dir) {
    return dir.toString().replaceAll("\\\\", ".").replaceAll("/", ".").replaceAll("^.*src(\\.main\\.java\\.)?", "") + ";"
}

def generate(out, className, fields, table) {
    out.println "package $packageName"
    out.println ""
    out.println "import javax.persistence.*;"
    out.println "import lombok.*;"

    Set types = new HashSet()

    fields.each() {
        types.add(it.type)
    }

    if (types.contains("BigDecimal")) {
        out.println "import java.math.BigDecimal;"
    }

    if (types.contains("LocalDateTime")) {
        out.println "import java.time.LocalDateTime;"
    }

    out.println ""
    out.println "@Entity"
    out.println "@Builder"
    out.println "@Getter"
//    out.println "@Setter"
    out.println "@DynamicInsert // Apply changed fields only"
    out.println "@DynamicUpdate // Apply changed fields only"
    out.println "@ToString"
    out.println "@NoArgsConstructor(access = AccessLevel.PROTECTED)"
    out.println "@Table (name = \"" + table.getName() + "\")"
    out.println "public class $className extends BaseEntity {"
    out.println ""
    fields.each() {
        // output comment
        if (isNotEmpty(it.commoent)) {
            out.println "\t/**${it.commoent.toString()}*/"
        }

        if (it.annos != "") out.println "   ${it.annos}"
        // output member variable
        out.println "\tprivate ${it.type} ${it.name};\n"
    }
    out.println ""
    out.println "}"
}

def calcFields(table) {
    DasUtil.getColumns(table).reduce([]) { fields, col ->
        def spec = Case.LOWER.apply(col.getDataType().getSpecification())

        def typeStr = typeMapping.find { p, t -> p.matcher(spec).find() }.value
        def comm = [
                colName : col.getName(),
//                name    : changeStyle(fileName(col.getName(), false), true),
                name : javaName(col.getName(), false),
                type    : typeStr,
                commoent: col.getComment(),
                annos   : "\t@Column(name = \"" + col.getName() + "\")"]
        if ("seq".equals(col.getName())){
            comm.annos = "\t@Id\n"
            //Need to increase the primary key
            comm.annos += "\t@GeneratedValue(strategy = GenerationType.IDENTITY)\n"
            comm.annos += "\t@Column(name = \"" + col.getName() + "\")"
        }
        if (col.getName().lastIndexOf("_") != -1 && "_seq".equals(col.getName().substring(col.getName().lastIndexOf("_")))){
            comm.annos = "\t/** FK setting */\n"
            comm.annos += "\t// @ManyToOne\n"
            comm.annos += "\t// @OneToMany\n"
            comm.annos += "\t// @JsonBackReference\n"
            comm.annos += "\t// @JoinColumn(name = \"\", updatable = false, insertable = false)"
        }
//    if ("reg_date".equals(Case.LOWER.apply(col.getName()))){
//       comm.annos += "\n\t@UpdateTimestamp"
//    }
        fields += [comm]
    }
}

def javaName(str, capitalize) {
    def s = com.intellij.psi.codeStyle.NameUtil.splitNameIntoWords(str)
            .collect { Case.LOWER.apply(it).capitalize() }
            .join("")
    capitalize || s.length() == 1? s : Case.LOWER.apply(s[0]) + s[1..-1]
}

def isNotEmpty(content) {
    return content != null && content.toString().trim().length() > 0
}