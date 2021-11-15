import {NavigationApp} from "../navigation-app";
import DatePicker from "react-datepicker";
import {useEffect, useState} from "react";
import {Link, useLocation} from 'react-router-dom';
import api from "../../services/api";
import {Pagination} from "../Pagination";

export const ListServiceType = ({ history, match }) => {

    const search = useLocation().search;
    const searchTerm = new URLSearchParams(search).get('searchTerm');

    const paginationSize = 10

    const [initialDate, setInitialDate] = useState(new Date());
    const [finalDate, setFinalDate] = useState(new Date());
    const [tableFilter, setTableFiler] = useState(undefined);
    const [serviceTypeData, setServiceTypeData] = useState(undefined);
    const [currentPage, setCurrentPage] = useState(0);
    const [totalPages, setTotalPages] = useState(undefined);
    const [totalElements, setTotalElements] = useState(undefined);

    const handlePrevNavigation = async () => {
        updateCurrentPage(currentPage-1)
    }

    const updateCurrentPage =  (newPage) => {
        if (newPage >= 0 && newPage<totalPages) {
            setCurrentPage(newPage)
        }
    }

    const handleNextNavigation = async () => {
        updateCurrentPage(currentPage+1)
    }

    const fetchItems = async () => {
        try {
            console.log('searchTerm:' + searchTerm)
            const params = {
                searchTerm: searchTerm,
                page: currentPage,
                size: paginationSize
            };
            console.log(params)
            return await api.get('/services', {params})
        } catch (error) {
            console.error("Ocorreu um erro ao buscar os items" + error);
        }
    }

    useEffect(() => {
        async function fetchData() {
            const response = await fetchItems()
            setTotalPages(response.data.totalPages)
            setTotalElements(response.data.totalElements)
            setServiceTypeData(response.data.content)
        }
        fetchData();
    }, [currentPage]);

    const filterServiceType = (serviceType) => {
        if (!tableFilter) return true;
        return ((serviceType.description).includes(tableFilter))
    }

    const filterTableElements = () => {
        let key = 0
        if (!serviceTypeData) return;
        return serviceTypeData
            .filter(filterServiceType)
            .map(serviceType =>
                <tr key={key++}>
                    <td>{serviceType.description}</td>
                    <td>{serviceType.durationInMinutes}</td>
                    <td>
                        <Link to={`/servico/${serviceType.id}`} className="btn btn-sm btn-primary mr-1">Edit</Link>
                    </td>
                </tr>
            )
    }

    return (
        <>
            <NavigationApp/>
            <div className='customForm'>
                <div className="table-wrapper">
                    <div className="table-title">
                        <div className="row">
                            <div className="col-sm-6">
                                <h2>Tipos de serviço</h2>
                            </div>
                            <div className="col-sm-6">
                                <a href="/registrarservico" className="btn btn-success" data-toggle="modal">
                                    <i className="material-icons">&#xE147;</i> <span>Adicionar</span></a>
                            </div>
                        </div>
                        <form className="form-inline">
                            <div className="form-group">
                                <p>Digite algo para procurar na tabela abaixo:</p>
                                <input className="form-control" id="myInput" type="text" placeholder="Search.."
                                       onChange={e => setTableFiler(e.target.value)}/>
                                <a href={`/listarservico?searchTerm=${tableFilter || ''}`} className="btn btn-primary">Procurar em todas paginas</a>
                            </div>
                        </form>
                    </div>
                    <table className="table table-striped table-hover">
                        <thead>
                        <tr>
                            <th>Descrição</th>
                            <th>Duração (minutos)</th>
                        </tr>
                        </thead>
                        <tbody>
                        {
                            filterTableElements()
                        }
                        </tbody>
                    </table>
                    <Pagination
                        handlePrevNavigation={handlePrevNavigation}
                        handleNextNavigation={handleNextNavigation}
                        totalElements={totalElements}
                        totalPages={totalPages}
                        maxItemsPerPage={paginationSize}
                    />
                </div>
            </div>
        </>
    )
}
