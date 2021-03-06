def b = new File(args[0]).getText()

b = b.replaceAll("property_value: created:by","created_by:")
b = b.replaceAll("property_value: creation:date", "creation_date:")
b = b.replaceAll("property_value: http://purl.obolibrary.org/obo/namespace","namespace:")
b = b.replaceAll("property_value: http://purl.obolibrary.org/obo/synonym","synonym:")
b = b.replaceAll("property_value: http://purl.obolibrary.org/obo/comment","comment:")
b = b.replaceAll("property_value: http://purl.org/dc/elements/1.1/date","date:")
b = b.replaceAll("property_value: note","note:")
b = b.replaceAll("property_value: alt:id","alt_id:")
b = b.replaceAll("property_value: \"note\"","!property_value: \"note\"")
b = b.replaceAll("property_value: is:obsolete \"true\"","is_obsolete: true")
b = b.replaceAll("property_value: http://purl.obolibrary.org/obo/xref","xref:")
b = b.replaceAll("property_value: http://purl.obolibrary.org/obo/def","def:")
b = b.replaceAll("xsd:string","")
b = b.replaceAll("\\\\\"","")
b = b.replaceAll("\"","")

def a = ""
b.eachLine { line ->
  line = line.trim()
  if (line.startsWith("def:")) {
    line = line.replaceAll("def: ","def: \"")
    def f1 = line.substring(0, line.lastIndexOf("[")).trim()
    def f2 = line.substring(line.lastIndexOf("[",line.lastIndexOf("]")))
    line = f1 + "\" " + f2
  }
  if (line.startsWith("synonym:")) {
    line = line.substring(0,line.lastIndexOf(" "))
    def val = line.substring(line.indexOf(":")+1).trim()
    line = "synonym: \"$val\" EXACT []"
  }
  if (line.startsWith("owl-axioms:")) {
    line = ""
  }
  if (line.startsWith("import:")) {
    line = "!$line"
  }
  line = line.trim()
  a += line+"\n"
}
println a