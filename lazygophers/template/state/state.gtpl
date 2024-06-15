package state

import (
	"github.com/lazygophers/log"
	"github.com/lazygophers/utils/app"{{ with .Config.I18n }}
	"github.com/lazygophers/lrpc/middleware/i18n"{{end}}
	"github.com/lazygophers/lrpc/middleware/storage/etcd"
	"github.com/lazygophers/utils/app"
)

type state struct {
	Config *Config
	Etcd   *etcd.Client

	// NOTE: Please fill in the state below
	{{ with .Config.I18n }}I18n *i18n.I18n{{ end }}
}

var State = new(state)

func Load() (err error) {
	log.SetPrefixMsg(app.Name)

	err = LoadConfig()
	if err != nil {
	    log.Errorf("err:%v", err)
	    return err
	}

	State.Etcd, err = etcd.ConnectWithLazy()
	if err != nil {
		log.Errorf("err:%v", err)
		return err
	}{{ with .Config.I18n}}

	err = LoadI18n()
	if err != nil {
		log.Errorf("err:%v", err)
		return err
	}{{end}}{{ with .Config.Cache }}

	err = ConnectCache()
	if err != nil {
	    log.Errorf("err:%v", err)
	    return err
	}{{ end }}{{ with .Config.Table}}

	err = ConnectDatebase()
	if err != nil {
	    log.Errorf("err:%v", err)
	    return err
	}{{ end }}

	return nil
}
