package co.zibi.mf.extension

fun String.removeSpaces(): String {
    return this.replace(" ", "")
}

fun String.removeSpacesAndHyphen(): String {
    return this.replace("[ -]".toRegex(), "")
}