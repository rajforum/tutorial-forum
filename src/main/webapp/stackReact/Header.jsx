class Header extends React.Component{
    render(){
        return(
    <table class="top_bar">
        <tr class="container">
            <td id="logo">
                <img src="./images/name1.png"  alt="Technical-Forum" />
            </td>
            <td class="search_tab">
                <form id="search" action="/search" method="get" class="searchbar" autocomplete="off" role="search" >
                    <input name="q" type="text" placeholder="Search…" value="" autocomplete="off" maxlength="240" />
                    <button type="submit" aria-label="Search..." >
                        <i class='material-icons'> search</i>
                    </button>
                </form>
            </td>
            <td id="hidden_box"></td>
            <td id="switch_btn">
                <span class="btn_main">Login</span>
                <span class="btn_main">Sign up</span>
                <span id="userImg"><img src="./images/userImage.png" /></span>
            </td>

        </tr>
    </table>


        );
    }
}

ReactDOM.render(
    <Header />,
    document.getElementById('header')
);