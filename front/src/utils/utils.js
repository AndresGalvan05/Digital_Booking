export const convert = function(str) {
    var date = new Date(str),
      mnth = ("0" + (date.getMonth() + 1)).slice(-2),
      day = ("0" + date.getDate()).slice(-2);
      if (date.getFullYear() <= 2000) {
        return ""
    }
    return [date.getFullYear(), mnth, day].join("-");
  }
