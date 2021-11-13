import history from './history'
export const Pagination = ({currentPage, handleNavigation, totalElements, totalPages, maxItemsPerPage}) => {

    const nextPage = async () => {
        console.log('WILL PAGE LOAD: ' + currentPage++)
        await handleNavigation(currentPage++)
    }

    const previousPage = async () => {
        console.log('WILL PAGE LOAD: ' + currentPage--)
        await handleNavigation(currentPage--)
    }

    const getTotalItems = () => {
        return maxItemsPerPage
    }

    const getPagesClick = () => {
        const pages = []
        for (let i = 0; i < totalPages; i++) {
            pages.push(<li className="page-item"><button href="#" className="page-link">{i+1}</button></li>)
        }
        return pages
    }

    return (
        <div className="clearfix">
            <div className="hint-text">Showing <b>{getTotalItems()}</b> out of <b>{totalElements}</b> entries</div>
            <ul className="pagination">
                <li className="page-item disabled"><button onClick={previousPage}>Previous</button></li>
                {
                    getPagesClick()
                }
                <li className="page-item"><button onClick={nextPage} className="page-link">Next</button></li>
            </ul>
        </div>
    );
}
