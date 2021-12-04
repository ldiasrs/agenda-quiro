import { format, parse } from "date-fns";
import pt from 'date-fns/locale/pt-BR';

export const parseAndFormatDate = (dateString) => {
    const date = parse(
        dateString,
        'yyyy-MM-dd',
        new Date()
    )
    return format(date, "dd/MM/yyyy - cccc", { locale: pt });
}

export const parseAndFormatHour = (hourString) => {
    const date = parse(
        hourString,
        'HH:mm:ss',
        new Date()
    )
    return format(date, "HH:mm");
}
